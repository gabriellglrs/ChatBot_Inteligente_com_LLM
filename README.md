![LinkedIn cover - 26](https://github.com/user-attachments/assets/8f1ab043-01bb-41e1-9c17-64eda8b495bb)


# Chatbot Inteligente 🤖

Um chatbot inteligente desenvolvido com Spring Boot que integra com modelos de IA local através do Ollama, oferecendo uma interface web para conversas e gerenciamento de usuários.

## 📋 Características

- **Interface Web Intuitiva**: Interface limpa e responsiva para interação com o chatbot
- **Autenticação e Autorização**: Sistema completo de login/registro com Spring Security
- **Histórico de Conversas**: Armazenamento e visualização do histórico completo de conversas
- **Integração com IA Local**: Utiliza Ollama para processamento de linguagem natural
- **Arquitetura RESTful**: APIs REST para operações CRUD de conversas
- **Persistência de Dados**: Banco de dados para usuários e conversas

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **Thymeleaf** - Template engine para frontend
- **Lombok** - Redução de boilerplate code
- **ModelMapper** - Mapeamento entre DTOs e entidades
- **BCrypt** - Criptografia de senhas
- **Ollama** - Integração com modelos de IA local
- **Banco de dados H2/MySQL** (configurável)

## 🚀 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java 17 ou superior**
- **Maven 3.6+**
- **Ollama** instalado e configurado
- **Git**

### Configuração do Ollama

1. Instale o Ollama seguindo as instruções em [ollama.ai](https://ollama.ai)
2. Baixe um modelo de sua preferência:
   ```bash
   ollama pull llama2
   # ou
   ollama pull mistral
   ```
3. Certifique-se de que o Ollama está rodando na porta padrão (11434)

## ⚙️ Configuração

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd Inteligente
   ```

2. **Configure o arquivo `application.properties`:**
   ```properties
   # Configuração do Ollama
   spring.ai.ollama.base-url=http://localhost:11434
   spring.ai.ollama.model=llama2
   
   # Configuração do banco de dados (exemplo com H2)
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   
   # JPA/Hibernate
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   
   # Configurações do servidor
   server.port=8080
   ```

3. **Instale as dependências:**
   ```bash
   mvn clean install
   ```

## 🏃‍♂️ Como Executar

1. **Inicie o Ollama** (se não estiver rodando):
   ```bash
   ollama serve
   ```

2. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse a aplicação:**
   - URL: `http://localhost:8080`
   - Para fazer login, primeiro registre um usuário em `/register`

## 🎯 Funcionalidades

### Autenticação
- **Registro de usuários** (`/register`)
- **Login seguro** (`/login`)
- **Logout**
- **Criptografia de senhas** com BCrypt

### Chatbot
- **Conversas em tempo real** com IA
- **Processamento de linguagem natural** via Ollama
- **Respostas contextualizadas**

### Histórico
- **Visualização do histórico** de todas as conversas
- **Busca por conversa específica**
- **Exclusão de conversas**

### APIs REST
- `GET /api/conversation/{id}` - Buscar conversa por ID
- `DELETE /api/conversation/{id}` - Deletar conversa

## 📁 Estrutura do Projeto

```
src/main/java/com/chatbot/Inteligente/
├── DTO/                     # Data Transfer Objects
│   ├── ConversationResponseDTO.java
│   ├── UserRequestDTO.java
│   └── UserResponseDTO.java
├── config/                  # Configurações
│   ├── mapper/ModelMapperConfig.java
│   ├── rest/RestTemplateConfig.java
│   └── security/SecurityConfig.java
├── controllers/             # Controladores REST e Web
│   ├── AuthController.java
│   └── ConversationController.java
├── entities/               # Entidades JPA
│   ├── Conversation.java
│   └── User.java
├── exceptions/             # Tratamento de exceções
│   └── RecursoNaoEncontradoException.java
├── repositories/           # Repositórios de dados
│   ├── ConversationRepository.java
│   └── UserRepository.java
└── services/              # Lógica de negócio
    ├── ChatbotService.java
    └── UserService.java
```

## 🔒 Segurança

- **Autenticação baseada em formulário**
- **Criptografia de senhas** com BCrypt (10 rounds)
- **Proteção CSRF** desabilitada para APIs REST
- **Controle de acesso** baseado em rotas
- **Validação de entrada** nos endpoints

## 🎨 Interface do Usuário

A aplicação possui uma interface web moderna construída com Thymeleaf, oferecendo:

- **Página de login** responsiva
- **Formulário de registro** com validação
- **Interface de chat** intuitiva
- **Histórico de conversas** organizador
- **Design responsivo** para dispositivos móveis

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 🐛 Problemas Conhecidos

- Certifique-se de que o Ollama está rodando antes de iniciar a aplicação
- A primeira resposta pode demorar mais devido ao carregamento do modelo
- Em caso de timeout, verifique a conectividade com o serviço Ollama

## 📞 Suporte

Se você encontrar algum problema ou tiver dúvidas:

1. Verifique se o Ollama está rodando corretamente
2. Consulte os logs da aplicação para erros específicos
3. Abra uma issue no repositório do projeto

## Autor
 Gabriel lucas rodrigues souza
 <br>
LinkedIn: https://www.linkedin.com/in/gabriellglrs/

---

Desenvolvido com ❤️ usando Spring Boot e Ollama
<br>
<br>
<div align="center">
  <img src="https://github.com/user-attachments/assets/ed7208b8-6bdc-4c82-98aa-8c8cb9c1428f" height="150"/>
</div>


<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=4C89F8&height=120&section=footer"/>
