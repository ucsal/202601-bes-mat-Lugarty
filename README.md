# Sistema das Olimpíadas – Refatoração SOLID

Refatoração do código legado aplicando os princípios SOLID, sem alterar a lógica de negócio.

## Principais Mudanças

- Separação da lógica de persistência e regras de negócio da classe `App`.
- Criação das camadas:
  - **Repositórios** (interfaces e implementações em memória) – responsáveis pelo armazenamento.
  - **Serviços** – contêm as regras de negócio.
  - **App** – apenas interface com o usuário (menus e entrada/saída).
- Inversão de dependência: os serviços dependem de abstrações (interfaces) de repositórios, não de implementações concretas.

## Princípios SOLID Aplicados

### S – Single Responsibility Principle (SRP)
- **Antes**: a classe `App` acumulava controle de fluxo, persistência, lógica de negócio e interação com o usuário.
- **Depois**: cada camada tem uma única responsabilidade:
  - `App`: interação com o usuário (menus).
  - `*Service`: regras de negócio (cadastro, aplicação de prova, cálculo de nota).
  - `*Repository`: persistência (salvar, buscar, listar).

### O – Open/Closed Principle (OCP)

### L – Liskov Substitution Principle (LSP)

### I – Interface Segregation Principle (ISP)

### D – Dependency Inversion Principle (DIP)
- **Antes**: `App` dependia diretamente de listas estáticas e implementações concretas de armazenamento.
- **Depois**: 
  - Criado interfaces para repositórios (`ParticipanteRepository`, `ProvaRepository`, etc.).
  - Os serviços recebem essas interfaces via construtor.
  - No `main`, instancido as implementações concretas (`InMemory*Repository`) e injetado nos serviços. Assim, é fácil trocar a forma de persistência sem alterar os serviços.

## Histórico de Commits
- **Commit 1**: Organização do código em pacotes (`domain`, `service`, `repository`, `ui`) e movimentação das classes de modelo para `domain`. Ajuste de pacotes e imports.
- **Commit 2**: Aplicação de SRP e DIP – criação de repositórios e serviços; separação da lógica de negócio e persistência da classe `App`. Inversão de dependência usando interfaces.

## Autor
- Nome: Anísio Oliveira Albuquerque Filho
- Matrícula: 200031540