# Quiz Backend — Spring Boot REST API

API REST para gestionar quizzes, preguntas y respuestas. Este repositorio funciona como muestra ejecutable de backend Java: arquitectura por capas, persistencia relacional, contenedores y pruebas automatizadas.

## Stack

- Java 21
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA / Hibernate
- Jakarta Validation
- PostgreSQL 15
- Docker / Docker Compose
- JUnit 5 / Mockito
- Testcontainers

## Arquitectura

```text
HTTP / JSON
    |
Controller
    |
Service
    |
Repository
    |
JPA / Hibernate
    |
PostgreSQL
```

Modelo relacional principal:

```text
Quiz (1) ──── (N) Pregunta (1) ──── (N) Respuesta
```

## Inicio rápido

Requisito: Docker con Docker Compose.

```bash
git clone https://github.com/jav-anibal/quiz-backend-springboot.git
cd quiz-backend-springboot
docker compose up --build
```

La API queda disponible en `http://localhost:8081`.

Docker Compose levanta PostgreSQL, espera a que la base de datos supere su healthcheck y después inicia la aplicación.

## Tests

Ejecutar la suite:

```bash
./mvnw test
```

En Windows:

```powershell
mvnw.cmd test
```

La suite combina dos niveles:

- **JUnit 5 + Mockito:** pruebas unitarias aisladas de la capa de servicio.
- **Testcontainers + PostgreSQL:** verificación de arranque e integración contra una instancia real y efímera de PostgreSQL.

Los tests de integración requieren Docker disponible.

## Endpoints

### `/api/quizzes`

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | `/api/quizzes` | Listar quizzes |
| GET | `/api/quizzes/{id}` | Obtener por ID |
| POST | `/api/quizzes` | Crear |
| PUT | `/api/quizzes/{id}` | Actualizar |
| DELETE | `/api/quizzes/{id}` | Eliminar |

### `/api/preguntas`

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | `/api/preguntas` | Listar preguntas |
| GET | `/api/preguntas/{id}` | Obtener por ID |
| POST | `/api/preguntas` | Crear |
| PUT | `/api/preguntas/{id}` | Actualizar |
| DELETE | `/api/preguntas/{id}` | Eliminar |

### `/api/respuestas`

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | `/api/respuestas` | Listar respuestas |
| GET | `/api/respuestas/{id}` | Obtener por ID |
| POST | `/api/respuestas` | Crear |
| PUT | `/api/respuestas/{id}` | Actualizar |
| DELETE | `/api/respuestas/{id}` | Eliminar |

## Configuración

La aplicación acepta configuración de base de datos mediante variables de entorno:

| Variable | Uso |
| --- | --- |
| `DB_URL` | JDBC URL de PostgreSQL |
| `DB_USER` | Usuario de base de datos |
| `DB_PASSWORD` | Contraseña de base de datos |

El repositorio incluye `.env.example` como referencia. No es necesario almacenar credenciales reales en Git.

## Qué demuestra este proyecto

El objetivo del repositorio no es representar un producto completo, sino hacer verificables competencias concretas de backend:

- diseño de API REST;
- separación Controller / Service / Repository;
- modelado relacional con JPA;
- validación de entrada;
- gestión centralizada de errores;
- PostgreSQL;
- contenedorización reproducible;
- pruebas unitarias e integración con una base de datos real.
