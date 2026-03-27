# Sistema das Olimpíadas – Refatoração SOLID

Refatoração do código legado aplicando os princípios SOLID, sem alterar a lógica de negócio.

## Principais Mudanças

## Princípios SOLID Aplicados

### S – Single Responsibility Principle (SRP)

### O – Open/Closed Principle (OCP)

### L – Liskov Substitution Principle (LSP)

### I – Interface Segregation Principle (ISP)

### D – Dependency Inversion Principle (DIP)

## Histórico de Commits
- **Commit 1**: Organização do código em pacotes (`domain`, `service`, `repository`, `ui`) e movimentação das classes de modelo para `domain`. Ajuste de pacotes e imports. (sem alteração funcional)
- **Commit 2**: Aplicação de SRP e DIP – criação de repositórios e serviços; separação da lógica de negócio e persistência da classe App. Inversão de dependência usando interfaces.

## Autor
- Nome: Anísio Oliveira Albuquerque Filho
- Matrícula: 200031540