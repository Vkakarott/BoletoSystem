
# Boleto System

Sistema de emissão e controle de boletos


## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu application.properties

spring.application.name=demo

spring.datasource.url=jdbc:postgresql://host:port/database_name
password=B%23ll4tr1x
spring.datasource.username=postgres
spring.datasource.password=****
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.hikari.maximum-pool-size=10

# JPA Configurações
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true




## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/Vkakarott/BoletoSystem.git
```

Entre no diretório do projeto

```bash
  cd BoletoSystem
```

Instale o springboot

```bash
  mvn install
```

Inicie o servidor

```bash
  mvn spring-boot:run
```


## Rotas

### criar novo cliente
```bash
curl -X POST "http://localhost:8080/clients" \
     -H "Content-Type: application/json" \
     -d '{
           "name": "John Doe",
           "email": "john.doe@example.com",
           "cpf": "123.456.789-00",
           "phone": "11987654321"
         }'
```

### listar clients
```bash
curl -X GET "http://localhost:8080/clients"
```

### criar instituição
```bash
curl -X POST http://localhost:8080/institutions \
     -H "Content-Type: application/json" \
     -d '{
           "name": "Minha Instituição",
           "cnpj": "12345678000199",
           "email": "contato@instituicao.com",
           "phone": "11987654321"
         }'
```

### listar instituições
```bash
curl -X GET "http://localhost:8080/institutions"
```

### criar boleto
```bash
curl -X POST http://localhost:8080/tickets \
     -H "Content-Type: application/json" \
     -d '{
           "name": "Minha Instituição",
           "cnpj": "12345678000199",
           "email": "contato@instituicao.com",
           "phone": "11987654321"
         }'
```

### listar boletos
```bash
curl -X GET "http://localhost:8080/tickets"
```

### gerar pdf boleto
```bash
curl -X GET "http://localhost:8080/boleto/gerar?id=3" -H "Content-Type: application/json"
```

