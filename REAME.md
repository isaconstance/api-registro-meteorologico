# API de Registro Meteorológico

API REST para registro e gerenciamento de medições meteorológicas.
Tecnologias: Java 25, Spring Boot, JPA, Flyway, H2, MySQL, Swagger e Docker.

**Autora:** Isabella – RM 559219

## Endpoints

| Método  | Rota           | Descrição           |
|---------|----------------|---------------------|
| GET     | /registros     | Lista todos         |
| GET     | /registros/{id} | Busca por ID        |
| POST    | /registros     | Cria um registro    |
| PUT     | /registros/{id} | Atualiza um registro |
| DELETE  | /registros/{id} | Remove um registro  |

## Profiles

- `test` (padrão): banco H2 em memória. O Flyway fica desligado e o Hibernate cria a tabela.
- `dev`: banco MySQL. O Flyway cria a tabela (V1) e insere 3 registros iniciais (V2).

Para usar o `dev`, defina `SPRING_PROFILES_ACTIVE=dev`.

## 1. Construir a imagem

```bash
docker build -t api-registro-meteorologico .
```

## 2. Executar com o profile test (H2)

```bash
docker run -d --name api-registro -p 8080:8080 -e SPRING_PROFILES_ACTIVE=test api-registro-meteorologico
```

## 3. Iniciar o MySQL

```bash
docker compose up -d
```

Aguarde cerca de 30 segundos até o MySQL aceitar conexões.

## 4. Executar com o profile dev (MySQL)

Com o MySQL iniciado:

```bash
docker run -d --name api-registro -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/meteorologia?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC" \
  api-registro-meteorologico
```

No PowerShell, troque o `\` do fim das linhas por `` ` ``.

Para trocar de profile, remova o container antes:

```bash
docker rm -f api-registro
```

## 5. Acessar o Swagger / OpenAPI

- Swagger UI: http://localhost:8080/swagger-ui.html
- JSON: http://localhost:8080/v3/api-docs

## Parar tudo

```bash
docker rm -f api-registro
docker compose down
```