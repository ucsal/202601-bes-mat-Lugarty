markdown
# Sistema das Olimpíadas – Refatoração SOLID

Refatoração do código legado aplicando os princípios SOLID, sem alterar a lógica de negócio.

## Estrutura do Projeto

src/main/java/br/com/ucsal/olimpiadas/
├── App.java # Classe principal (UI)
├── domain/ # Entidades de domínio
│ ├── Participante.java
│ ├── Prova.java
│ ├── Questao.java # Interface (novo)
│ ├── QuestaoMultiplaEscolha.java # Implementação concreta (antiga Questao)
│ ├── Resposta.java
│ └── Tentativa.java
├── repository/ # Interfaces de persistência
│ ├── ParticipanteRepository.java
│ ├── ProvaRepository.java
│ ├── QuestaoRepository.java
│ └── TentativaRepository.java
├── repository/impl/ # Implementações em memória
│ ├── InMemoryParticipanteRepository.java
│ ├── InMemoryProvaRepository.java
│ ├── InMemoryQuestaoRepository.java
│ └── InMemoryTentativaRepository.java
└── service/ # Regras de negócio
├── ParticipanteService.java
├── ProvaService.java
├── QuestaoService.java
└── TentativaService.java

## Principais Mudanças

- Separação da lógica de persistência e regras de negócio da classe `App`.
- Criação das camadas:
  - **Repositórios** (interfaces e implementações em memória) – responsáveis pelo armazenamento.
  - **Serviços** – contêm as regras de negócio.
  - **App** – apenas interface com o usuário (menus e entrada/saída).
- Inversão de dependência: os serviços dependem de abstrações (interfaces) de repositórios, não de implementações concretas.
- Criação da interface `Questao` e renomeação da classe original para `QuestaoMultiplaEscolha`, permitindo que novos tipos de questão sejam adicionados sem modificar o código existente (OCP) e garantindo que qualquer implementação de `Questao` possa ser usada no lugar da original (LSP).

## Princípios SOLID Aplicados

### S – Single Responsibility Principle (SRP)
- **Antes**: a classe `App` acumulava controle de fluxo, persistência (listas estáticas), lógica de negócio e interação com o usuário.
- **Depois**: cada camada tem uma única responsabilidade:
  - `App`: interação com o usuário (menus).
  - `*Service`: regras de negócio (cadastro, aplicação de prova, cálculo de nota).
  - `*Repository`: persistência (salvar, buscar, listar).

### O – Open/Closed Principle (OCP)
- **Antes**: a classe `Questao` era concreta e qualquer novo tipo de questão exigiria modificação no código de `Questao`, `QuestaoService` e `App`.
- **Depois**: criada a interface `Questao` (aberta para extensão) e a implementação `QuestaoMultiplaEscolha` (fechada para modificação). Novos tipos de questão podem ser criados implementando `Questao` sem alterar os serviços ou a UI.

### L – Liskov Substitution Principle (LSP)
- **Antes**: não havia abstração; a classe concreta `Questao` não permitia substituição.
- **Depois**: a interface `Questao` define o contrato e a classe `QuestaoMultiplaEscolha` o implementa. Qualquer outra implementação (ex: `QuestaoVerdadeiroFalso`) pode ser usada onde `Questao` é esperada, sem quebrar o comportamento do sistema.

### I – Interface Segregation Principle (ISP)
*(A ser aplicado no próximo commit – segregar interfaces de repositório em leitura e escrita)*

### D – Dependency Inversion Principle (DIP)
- **Antes**: `App` dependia diretamente de listas estáticas e implementações concretas de armazenamento.
- **Depois**: 
  - Criadas interfaces para repositórios (`ParticipanteRepository`, `ProvaRepository`, etc.).
  - Os serviços recebem essas interfaces via construtor (injeção de dependência).
  - No `main`, instanciamos as implementações concretas (`InMemory*Repository`) e injetamos nos serviços. Assim, é fácil trocar a forma de persistência sem alterar os serviços.

## Histórico de Commits

| Commit | Descrição | Arquivos Modificados/Criados |
|--------|-----------|-------------------------------|
| **Commit 1** | Organização do código em pacotes (`domain`, `service`, `repository`, `ui`) e movimentação das classes de modelo para `domain`. | `App.java`, `Participante.java`, `Prova.java`, `Questao.java`, `Tentativa.java`, `Resposta.java` (movidos para `domain/`) |
| **Commit 2** | Aplicação de SRP e DIP – criação de repositórios e serviços; separação da lógica de negócio e persistência da classe `App`. | `ParticipanteRepository.java`, `ProvaRepository.java`, `QuestaoRepository.java`, `TentativaRepository.java` (interfaces)<br>`InMemoryParticipanteRepository.java`, `InMemoryProvaRepository.java`, `InMemoryQuestaoRepository.java`, `InMemoryTentativaRepository.java` (impl)<br>`ParticipanteService.java`, `ProvaService.java`, `QuestaoService.java`, `TentativaService.java`<br>`App.java` (refatorado) |
| **Commit 3** | Aplicação de OCP e LSP – criação da interface `Questao` e renomeação da classe original para `QuestaoMultiplaEscolha`. | `Questao.java` (transformada em interface)<br>`QuestaoMultiplaEscolha.java` (renomeada e adaptada)<br>`App.java` (ajustes para usar a nova implementação)<br>`QuestaoService.java` (ajuste para criar `QuestaoMultiplaEscolha`)<br>`TentativaService.java` (ajuste para usar a interface `Questao`) |

## Autor
- Nome: Anísio Oliveira Albuquerque Filho
- Matrícula: 200031540