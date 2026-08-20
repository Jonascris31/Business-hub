\# Business Hub



> Plataforma empresarial modular em construção, projetada para servir como base tecnológica para um SaaS e, futuramente, para soluções de software sob medida.



\## Visão



O Business Hub nasceu com uma ideia simples: construir uma base de software empresarial que possa evoluir para dois caminhos sem duplicar a tecnologia:



\* \*\*Business Hub SaaS\*\* — produto padronizado para pequenas empresas e negócios em crescimento.

\* \*\*Soluções sob medida\*\* — sistemas personalizados construídos sobre uma base tecnológica reutilizável.



A arquitetura será evoluída gradualmente. A separação entre \*\*Business Hub Core\*\* e as camadas específicas do SaaS será realizada depois que a base atual estiver estável, testada e versionada.



\## Estado atual



A primeira versão funcional já possui um fluxo completo de gerenciamento de clientes através de uma API REST.



\### Implementado



\* CRUD de clientes

\* Criação de clientes com validação

\* Consulta de todos os clientes

\* Consulta de cliente por ID

\* Atualização de clientes

\* Exclusão de clientes

\* Validação de campos obrigatórios e formato de e-mail

\* Proteção contra e-mail duplicado

\* Tratamento global de exceções

\* Persistência com PostgreSQL

\* Migração de banco de dados com Flyway

\* Testes automatizados de Service e Controller

\* Versionamento com Git e GitHub



\## API atual



Base URL local:



```text

http://localhost:8080

```



\### Clientes



| Método   | Endpoint            | Descrição               |

| -------- | ------------------- | ----------------------- |

| `POST`   | `/api/clients`      | Cria um cliente         |

| `GET`    | `/api/clients`      | Lista os clientes       |

| `GET`    | `/api/clients/{id}` | Busca um cliente por ID |

| `PUT`    | `/api/clients/{id}` | Atualiza um cliente     |

| `DELETE` | `/api/clients/{id}` | Remove um cliente       |



\### Exemplo de criação



```json

{

&#x20; "name": "Cliente Teste",

&#x20; "email": "teste@exemplo.com",

&#x20; "phone": "31955555555"

}

```



\### Respostas de erro



\* `400 Bad Request` — dados inválidos

\* `404 Not Found` — recurso não encontrado

\* `409 Conflict` — recurso duplicado



Exemplo:



```json

{

&#x20; "error": "Dados inválidos.",

&#x20; "fields": {

&#x20;   "email": "E-mail inválido",

&#x20;   "name": "Nome é obrigatório"

&#x20; }

}

```



\## Arquitetura atual



```text

business-hub

│

├── controller

│   └── ClientController

│

├── service

│   └── ClientService

│

├── repository

│   └── ClientRepository

│

├── entity

│   └── Client

│

├── dto

│   ├── ClientRequest

│   └── ClientResponse

│

├── exception

│   ├── GlobalExceptionHandler

│   ├── ResourceNotFoundException

│   └── DuplicateResourceException

│

└── db/migration

&#x20;   └── V1\_\_create\_clients\_table.sql

```



\## Stack



\* Java 21

\* Spring Boot 4.1.0

\* Spring MVC

\* Spring Data JPA

\* Bean Validation

\* Hibernate

\* PostgreSQL 17

\* Flyway

\* Maven

\* JUnit 5

\* Mockito

\* Git / GitHub

\* Docker Compose



\## Como executar



\### Pré-requisitos



\* JDK 21

\* Docker e Docker Compose

\* Git



\### Clonar



```bash

git clone https://github.com/Jonascris31/Business-hub.git

cd Business-hub

```



\### Subir o PostgreSQL



```bash

docker compose up -d

```



\### Executar a aplicação



Windows:



```cmd

mvnw.cmd spring-boot:run

```



Linux/macOS:



```bash

./mvnw spring-boot:run

```



A aplicação estará disponível em:



```text

http://localhost:8080

```



\### Executar os testes



Windows:



```cmd

mvnw.cmd test

```



Linux/macOS:



```bash

./mvnw test

```



\## Testes



Os testes fazem parte da evolução arquitetural do projeto e têm dois objetivos:



1\. garantir que as regras de negócio continuem funcionando durante a refatoração;

2\. criar uma base segura para a futura separação entre Core e SaaS.



Estado atual:



```text

7 testes

0 falhas

0 erros

```



\## Roadmap



\### Fase 1 — Fundação



\* \[x] Estrutura Spring Boot

\* \[x] PostgreSQL

\* \[x] Flyway

\* \[x] API REST de clientes

\* \[x] DTOs

\* \[x] Validação

\* \[x] Tratamento global de exceções

\* \[x] Testes automatizados

\* \[x] Versionamento Git

\* \[x] Repositório GitHub

\* \[x] README inicial



\### Fase 2 — Expansão do domínio



\* \[ ] Serviços

\* \[ ] Produtos

\* \[ ] Orçamentos

\* \[ ] Projetos

\* \[ ] Usuários

\* \[ ] Autenticação e autorização

\* \[ ] Auditoria



\### Fase 3 — Business Hub Core



\* \[ ] Identificar regras realmente reutilizáveis

\* \[ ] Separar domínio e contratos genéricos

\* \[ ] Criar módulo `business-hub-core`

\* \[ ] Garantir independência do Core em relação ao SaaS

\* \[ ] Criar testes de contrato e integração entre módulos



\### Fase 4 — Business Hub SaaS



\* \[ ] Multi-tenant

\* \[ ] Planos e limites

\* \[ ] Assinaturas

\* \[ ] Billing

\* \[ ] Dashboard

\* \[ ] Gestão de usuários e permissões

\* \[ ] Integrações externas



\### Fase 5 — Plataforma para software sob medida



\* \[ ] Definir mecanismo de extensão do Core

\* \[ ] Criar módulos específicos por cliente

\* \[ ] Reutilizar o mesmo núcleo em aplicações personalizadas

\* \[ ] Documentar padrões de extensão



\## Direção arquitetural



O objetivo de longo prazo é chegar a:



```text

&#x20;                   BUSINESS HUB CORE

&#x20;                          │

&#x20;            ┌─────────────┴─────────────┐

&#x20;            │                           │

&#x20;     BUSINESS HUB SaaS          SOFTWARE SOB MEDIDA

&#x20;            │                           │

&#x20;     regras específicas          módulos específicos

&#x20;     do produto SaaS             de cada projeto

```



O princípio central será:



> \*\*O Core não deve depender do SaaS.\*\*



Isso permitirá que a mesma base tecnológica seja utilizada em diferentes produtos sem transformar o núcleo em uma coleção de regras específicas de assinatura, cobrança ou operação de um único produto.



\## Filosofia do projeto



O Business Hub está sendo desenvolvido como um produto real, não apenas como um exercício de CRUD.



Cada etapa deve priorizar:



\* simplicidade antes de complexidade;

\* testes antes de grandes refatorações;

\* separação clara de responsabilidades;

\* evolução incremental;

\* reutilização consciente;

\* documentação das decisões arquiteturais.



\## Projeto em construção



O Business Hub faz parte de uma visão maior de construção de tecnologia reutilizável dentro do ecossistema \*\*Blood of the Abyss Studios\*\*.



A prioridade atual é consolidar a fundação do Business Hub. A separação Core × SaaS será feita somente quando a base atual estiver suficientemente madura para que a modularização seja uma consequência da arquitetura, e não uma complexidade adicionada prematuramente.



\---



\*\*Status:\*\* Em desenvolvimento



\*\*Versão:\*\* 0.1.0 — Foundation



\*\*Autor:\*\* Jonas Cristian



