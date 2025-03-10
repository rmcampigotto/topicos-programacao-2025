# Sistema de Campeonatos e Times

## Descrição do Projeto

Este projeto é um sistema desenvolvido para a disciplina **Tópicos de Programação**, ministrada pelo professor Eliel, no 7º semestre do curso de **Engenharia de Software** da **Unicesumar**. O objetivo é criar uma aplicação capaz de registrar e gerenciar **Times** participantes de um ou mais **Campeonatos** simultâneos, atendendo aos seguintes requisitos:

- Cada **Campeonato** deve possuir:
  - Nome
  - Data inicial
  - Data final
  - Lista de times participantes
- O sistema deve permitir o cadastro de **Times** e sua associação a **Campeonatos**.

A aplicação foi implementada utilizando **Spring Boot** com **Java**, banco de dados em memória **H2**, e segue uma arquitetura RESTful para facilitar a interação via API.

---

## Tecnologias Utilizadas

- **Java 17** (ou versão compatível com o Spring Boot)
- **Spring Boot 3.x** (incluindo Spring Web, Spring Data JPA)
- **H2 Database** (banco de dados em memória)
- **Maven** (gerenciamento de dependências)
- **Jakarta Persistence API (JPA)** (para mapeamento objeto-relacional)

---

## Estrutura do Projeto

O projeto está organizado no pacote base `com.example.times.e.campeonatos`, com os seguintes módulos principais:

- **`controller`**: Contém os controladores REST (`CampeonatoController` e `TimeController`) que definem os endpoints da API.
- **`model`**: Contém as entidades JPA (`Campeonato` e `Time`) que representam os dados do sistema.
- **`service`**: Contém a lógica de negócio (`CampeonatoService` e `TimeService`).
- **`repository`**: Contém as interfaces de acesso ao banco de dados (`CampeonatoRepository` e `TimeRepository`).

### Relacionamentos
- Um **Campeonato** pode ter vários **Times** (relação `@ManyToMany`).
- Um **Time** pode participar de vários **Campeonatos**.
- O **Campeonato** mantém uma classificação com a pontuação de cada time (usando `@ElementCollection`).

---

## Como Executar o Projeto

### Pré-requisitos
- **JDK 17** ou superior instalado
- **Maven** instalado
- IDE opcional (ex.: IntelliJ IDEA, Eclipse)

### Passos
1. **Clone o repositório** (se aplicável):
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd sistema-campeonatos

2. **Compile e execute o projeto**:
```bash

mvn clean install
mvn spring-boot:run
```
3. **Acesse a aplicação**:
   
A API estará disponível em `http://localhost:8080`.

O console do H2 pode ser acessado em `http://localhost:8080/h2-console` com as credenciais:
JDBC URL: jdbc:h2:mem:campeonato

Usuário: sa

Senha: (em branco)

--

## Endpoints da API
### Times
`GET /times`
- Lista todos os times cadastrados.
- **Resposta**: Lista de objetos Time.

`POST /times`
- Cria um novo time.
- Corpo da Requisição:
  ```json
  {
    "nome": "Flamengo"
  }
  ```
  
- **Resposta**: Objeto Time criado.

### Campeonatos
`GET /campeonatos`
- Lista todos os campeonatos cadastrados.
- **Resposta**: Lista de objetos Campeonato.

`POST /campeonatos`
- Cria um novo campeonato.
- Corpo da Requisição:
```json

{
    "nome": "Campeonato Brasileiro 2025",
    "dataInicio": "2025-04-01",
    "dataFim": "2025-12-01"
}
```

- **Resposta**: Objeto Campeonato criado.

`POST /campeonatos/{id}/adicionar-time`
- Adiciona um time a um campeonato existente.
- **Parâmetro**: id (ID do campeonato).
- **Corpo da Requisição**:
```json

{
    "nome": "Flamengo"
}
```


- **Resposta**: Objeto Campeonato atualizado.

## Funcionalidades Adicionais
- **Classificação**: O sistema mantém uma pontuação para cada time em um campeonato, que pode ser atualizada via método registrarPontuacao na classe Campeonato.

- **Ordenação**: O método getClassificacaoOrdenada retorna a classificação ordenada por pontuação decrescente.

## Considerações Finais
Este projeto atende ao enunciado da atividade, permitindo o cadastro de times e campeonatos, bem como a associação entre eles. Ele foi desenvolvido com boas práticas de programação, como injeção de dependências, uso de anotações do Spring, e uma API RESTful clara e funcional.
--

- **Autor**: Pedro Gomes
- **Disciplina**: Tópicos de Programação
- **Professor**: Eliel
- **Curso**: Engenharia de Software - 7º Semestre
- **Instituição**: Unicesumar
- **Data**: Março de 2025
