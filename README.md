# Auth & Roles API 🛡️

Simula uma API de autenticação e autorização desenvolvida com **Spring Boot 4** e **Spring Security**. O 
projeto implementa o gerenciamento de usuários com diferentes níveis de acesso (Roles) utilizando autenticação **Stateless** com tokens **JWT**.

## 🚀 Tecnologias
* **Java 25**
* **Spring Boot 4**
* **Spring Security**: Controle de acesso e segurança.
* **Auth0 JWT**: Geração e validação de tokens seguros.
* **JPA/Hibernate**: Persistência de dados com suporte a UUID.
* **BCrypt**: Algoritmo de hash para armazenamento de senhas.

## 🔑 Características de Segurança

### 1. Autenticação Stateless
A API não armazena sessões no servidor. A identidade do usuário é verificada através de um cabeçalho `Authorization: Bearer <token>` em cada requisição protegida.

### 2. Hierarquia de Permissões (RBAC)
O sistema utiliza o conceito de **Role-Based Access Control**:
- **ROLE_BASIC**: Usuário padrão do sistema.
- **ROLE_ADMIN**: Possui acesso administrativo e pode elevar o privilégio de outros usuários.

### 3. Proteção por Método
Utilização `@EnableMethodSecurity` para permitir o uso de anotações como `@PreAuthorize` diretamente nos 
controllers, garantindo uma camada extra de proteção.

---

## 🛠️ Endpoints da API

| Método | Endpoint           | Acesso    | Descrição                                                                     |
|:-------|:-------------------|:----------|:------------------------------------------------------------------------------|
| `POST` | `/auth/register`   | Público   | Registra um novo usuário no sistema.                                          |
| `POST` | `/auth/login`      | Público   | Autentica o usuário e retorna o token JWT.                                    |
| `PUT`  | `/auth/changeRole` | **ADMIN** | Altera o papel (Role) de um usuário específico.                               |
| `GET`  | `/games`           | **BASIC** | Simula uma rota protegida onde só usuários registrados<br/>podem ter acesso.  |
| `POST` | `/games`           | **ADMIN** | Simula uma rota protegida onde só usuários ADMIN<br/>podem adicionar um game. |

---

## 📝 Detalhes Técnicos
Estrutura do Token JWT

O token gerado pelo TokenService contém as seguintes informações no payload:

    iss: Identificador do emissor (AuthAndRolesWithJwt-API).

    sub: E-mail do usuário autenticado.

    role: O cargo atual do usuário (armazenado como Claim).

    exp: Tempo de expiração (configurado para 1 hora).