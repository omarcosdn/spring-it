# Spring-IT

## 📋 Descrição

**Spring-IT** é uma aplicação Spring Boot que demonstra a integração entre **RabbitMQ** e **JPA/Hibernate** para processamento de mensagens assíncronas. O projeto implementa um sistema de mensageria completo com produtor, consumidor e persistência de dados.

## 🏗️ Arquitetura

### Componentes Principais

- **RabbitMQ Producer**: Envia mensagens para filas RabbitMQ
- **RabbitMQ Consumer**: Consome mensagens das filas e as persiste no banco de dados
- **JPA/Hibernate**: Persistência de dados com PostgreSQL
- **TestContainers**: Testes de integração com containers isolados

### Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/github/omarcosdn/springit/
│   │   ├── Application.java
│   │   ├── domain/
│   │   │   ├── entities/
│   │   │   │   └── Message.java
│   │   │   └── repositories/
│   │   │       └── MessageRepository.java
│   │   └── infrastructure/
│   │       ├── config/
│   │       │   └── RabbitMQConfig.java
│   │       └── messaging/
│   │           ├── producer/
│   │           │   └── RabbitMQProducer.java
│   │           └── consumer/
│   │               └── RabbitMQConsumer.java
│   └── resources/
│       └── application.yml
└── test/
    ├── java/com/github/omarcosdn/springit/
    │   ├── RabbitMQIntegrationTest.java
    │   └── TestContainersConfiguration.java
    └── resources/
        └── application-test.yml
```

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring AMQP** (RabbitMQ)
- **Spring Data JPA**
- **PostgreSQL**
- **TestContainers**
- **JUnit 5**
- **Lombok**

## 📦 Pré-requisitos

- Java 21 ou superior
- Gradle 8.x
- Docker (para testes com TestContainers)

## 🔧 Configuração e Execução

### 1. Clone o Repositório

```bash
git clone https://github.com/omarcosdn/spring-it.git
cd spring-it
```

## 🧪 Executando Testes

### Testes de Integração

```bash
# Executar todos os testes (incluindo integração)
./gradlew test

# Executar apenas testes de integração
./gradlew test --tests "*IntegrationTest"
```

### Comandos Úteis

```bash
# Limpar e recompilar
./gradlew clean build

# Executar com profile específico
./gradlew bootRun --args='--spring.profiles.active=dev'

# Verificar dependências
./gradlew dependencies

# Análise estática
./gradlew check
```

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.