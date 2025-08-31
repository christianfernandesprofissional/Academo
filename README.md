# 🎓 Academo API

API backend do sistema **Academo**, uma plataforma de gerenciamento acadêmico que ajuda estudantes a organizar matérias, notas e arquivos da faculdade.  
Com o Academo, é possível agrupar matérias, receber notificações de atividades por e-mail e manter tudo centralizado em um só lugar.  

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** + **Spring Security**
- **JWT (JSON Web Token)** para autenticação
- **PostgreSQL** como banco de dados relacional
- **Flyway** para versionamento de schema do banco
- **Maven** para gerenciamento de dependências
- **Jakarta Mail** para envio de notificações por e-mail

---

## 🔐 Segurança

A API utiliza **Spring Security** junto com **JWT** para autenticação e autorização:  
- Cada usuário possui credenciais únicas.  
- O login gera um **token JWT**, necessário para acessar endpoints protegidos.  
- Tokens inválidos ou expirados retornam erros de autorização.  

---

## 📂 Funcionalidades

- 📚 **Gerenciamento de matérias**  
  - Criar, atualizar, listar e remover matérias.  
  - Organização de **grupos de matérias**.  

- 📝 **Controle de notas e atividades**  
  - Registrar notas de cada disciplina.  
  - Definir atividades com prazos.  

- 📁 **Arquivos da faculdade**  
  - Upload e organização de documentos relacionados às matérias.  

- 📧 **Notificações por e-mail**  
  - Lembretes automáticos de atividades próximas ao prazo.  

---

## ⚙️ Configuração do Projeto

### Pré-requisitos
- **Java 21** instalado  
- **Maven 3.9+**  
- **PostgreSQL** em execução  

### Passos
1. Clone o repositório:  
   ```bash
   git clone https://github.com/seu-usuario/academo-api.git
   cd academo-api
   ```

2. Configure as variáveis de ambiente no arquivo `application.yml` ou via `ENV`:  
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/academo
       username: seu_usuario
       password: sua_senha
     jpa:
       hibernate:
         ddl-auto: validate
     flyway:
       enabled: true

   jwt:
     secret: sua_chave_secreta
     expiration: 86400000 # 24h em ms

   mail:
     host: smtp.seuprovedor.com
     port: 587
     username: seu_email
     password: sua_senha
   ```

3. Execute a aplicação:  
   ```bash
   mvn spring-boot:run
   ```

---

## 📡 Endpoints Principais

### Autenticação
- `POST /auth/login` → autentica o usuário e retorna token JWT  
- `POST /auth/register` → cadastra novo usuário  

### Matérias
- `GET /subjects` → lista matérias do usuário autenticado  
- `POST /subjects` → cria nova matéria  
- `PUT /subjects/{id}` → atualiza matéria  
- `DELETE /subjects/{id}` → remove matéria  

### Atividades
- `GET /activities` → lista atividades  
- `POST /activities` → cria atividade com prazo  
- **Notificações por e-mail** são enviadas automaticamente antes do prazo.  

---

## 🛠️ Migrações com Flyway

Cada mudança no banco deve ser registrada em um arquivo de migração dentro de:  
```
src/main/resources/db/migration
```

Exemplo de arquivo: `V1__create_users_table.sql`

---

## 📬 Contribuição

1. Fork este repositório  
2. Crie uma branch (`git checkout -b feature/nova-funcionalidade`)  
3. Commit suas mudanças (`git commit -m 'feat: nova funcionalidade'`)  
4. Push para a branch (`git push origin feature/nova-funcionalidade`)  
5. Abra um **Pull Request**  

---

## 📄 Licença

Este projeto está sob a licença MIT.  
Sinta-se livre para usar e contribuir!  
