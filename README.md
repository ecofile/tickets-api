# Tickets API

API REST de gestión de tickets de soporte, desarrollada como proyecto de aprendizaje y portfolio con **Spring Boot 4** y **Java 21**.

## Tecnologías

- Java 21
- Spring Boot 4.1 (Spring Web, Spring Data JPA, Validation)
- Hibernate / JPA
- H2 (base de datos en memoria)
- Maven

## Cómo ejecutarlo

Requisitos: JDK 21.

```bash
./mvnw spring-boot:run
```

La API arranca en `http://localhost:8080`.

## Endpoints

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/tickets` | Lista todos los tickets |
| GET | `/api/tickets/{id}` | Obtiene un ticket (404 si no existe) |
| POST | `/api/tickets` | Crea un ticket (`{"titulo": "..."}`), valida que el título no esté vacío |

## Próximos pasos

- [ ] Capa de servicio y DTOs
- [ ] Gestión global de errores con `@RestControllerAdvice` y `ProblemDetail`
- [ ] Relaciones entre entidades (tickets, usuarios, comentarios)
- [ ] Tests con JUnit 5 y Mockito
- [ ] Seguridad con Spring Security y JWT
- [ ] PostgreSQL con Docker y migraciones con Flyway