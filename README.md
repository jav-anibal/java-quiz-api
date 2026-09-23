# Java Quiz API

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql) ![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker)

API REST para gestionar quizzes, preguntas y respuestas con Java 21, Spring Boot 3 y PostgreSQL.

## Características

- Arquitectura por capas: controllers, services, mappers y repositories.
- DTOs para desacoplar el contrato HTTP de las entidades JPA.
- Validación de entrada con Jakarta Validation.
- Transacciones explícitas y consultas de solo lectura.
- Documentación OpenAPI con Swagger UI.
- Tests unitarios y de integración con Testcontainers.

## Stack tecnológico

| Capa | Tecnología |
| --- | --- |
| Lenguaje | Java 21 |
| Framework | Spring Boot 3.5.6 |
| Persistencia | Spring Data JPA / Hibernate |
| Base de datos | PostgreSQL 15 |
| Validación | Jakarta Validation |
| Documentación | Springdoc OpenAPI / Swagger UI |
| Testing | JUnit 5, Mockito, Testcontainers |
| Contenedores | Docker, Docker Compose |

## Arquitectura

```text
HTTP / JSON
    │
    ▼
Controller → Service → Mapper → Repository → PostgreSQL
```

### Modelo relacional

```text
Quiz (1) ────< Pregunta (1) ────< Respuesta
```

## Inicio rápido

### Docker Compose

Requisitos: Docker y Docker Compose.

```bash
git clone https://github.com/jav-anibal/java-quiz-api.git
cd java-quiz-api
docker compose up --build
```

| Servicio | URL |
| --- | --- |
| API | <http://localhost:8081> |
| Swagger UI | <http://localhost:8081/swagger-ui/index.html> |

### Ejecución local

Requisitos: Java 21 y PostgreSQL en localhost:5433.

1. Copia .env.example a .env.
2. Inicia la base de datos:

   ```bash
   docker compose up -d db
   ```

3. Arranca la aplicación:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

| Servicio | URL |
| --- | --- |
| API | <http://localhost:8080> |
| Swagger UI | <http://localhost:8080/swagger-ui/index.html> |

## Endpoints

### Quizzes

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | /api/quizzes | Lista quizzes con sus preguntas. |
| GET | /api/quizzes/{id} | Obtiene un quiz por ID. |
| POST | /api/quizzes | Crea un quiz con preguntas y respuestas. |
| PUT | /api/quizzes/{id} | Actualiza un quiz. |
| DELETE | /api/quizzes/{id} | Elimina un quiz y su contenido. |

### Preguntas

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | /api/preguntas | Lista preguntas con sus respuestas. |
| GET | /api/preguntas/{id} | Obtiene una pregunta por ID. |
| POST | /api/preguntas/quiz/{quizId} | Crea una pregunta en un quiz. |
| PUT | /api/preguntas/{id} | Actualiza una pregunta. |
| DELETE | /api/preguntas/{id} | Elimina una pregunta. |

### Respuestas

| Método | Ruta | Descripción |
| --- | --- | --- |
| GET | /api/respuestas | Lista respuestas. |
| GET | /api/respuestas/{id} | Obtiene una respuesta por ID. |
| POST | /api/respuestas/pregunta/{preguntaId} | Crea una respuesta en una pregunta. |
| PUT | /api/respuestas/{id} | Actualiza una respuesta. |
| DELETE | /api/respuestas/{id} | Elimina una respuesta. |

## Ejemplo de uso

### Crear un quiz completo

```http
POST /api/quizzes
Content-Type: application/json
```

```json
{
  "titulo": "Fundamentos de Java",
  "categoria": "FUNDAMENTOS",
  "preguntas": [
    {
      "enunciado": "¿Qué es una clase en Java?",
      "respuestas": [
        {
          "texto": "Una plantilla para objetos",
          "opcion": "B",
          "esCorrecta": true
        },
        {
          "texto": "Una estructura de control",
          "opcion": "A",
          "esCorrecta": false
        }
      ]
    }
  ]
}
```

## Testing

```powershell
.\mvnw.cmd test
```

| Tipo | Herramienta | Qué verifica |
| --- | --- | --- |
| Unitario | JUnit 5 + Mockito | Lógica de servicios. |
| Integración | Spring Boot Test + Testcontainers | Contexto completo contra PostgreSQL real. |

Los tests de integración se ejecutan solo cuando Docker está disponible.

## Configuración

| Variable | Descripción | Valor por defecto |
| --- | --- | --- |
| DB_NAME | Nombre de la base de datos. | quiz_backend_db |
| DB_USER | Usuario de PostgreSQL. | postgres |
| DB_PASSWORD | Contraseña de PostgreSQL. | admin en Docker Compose |
| DB_URL | JDBC URL de PostgreSQL. | Depende del entorno |

El archivo .env.example contiene una configuración de referencia. No subas credenciales reales.

## Decisiones técnicas

- **DTOs separados de las entidades:** contrato HTTP desacoplado del modelo JPA.
- **Mappers manuales:** conversiones explícitas y pocas dependencias.
- **Quiz como raíz del agregado:** creación del quiz y su contenido en una operación.
- **Endpoints REST planos:** recursos fáciles de consumir, sin anidaciones profundas.
- **Transacciones explícitas:** consultas configuradas como solo lectura.

## Estado del proyecto

### Implementado

- [x] CRUD de quizzes, preguntas y respuestas.
- [x] DTOs y mappers entidad ⇄ DTO.
- [x] Validación de entrada.
- [x] OpenAPI y Swagger UI.
- [x] Actuator.
- [x] Dockerfile multi-stage y Docker Compose.
- [x] Tests unitarios, de integración y de controllers con MockMvc.
- [x] CI con GitHub Actions.
- [x] Manejo global de errores para validación (400), recursos inexistentes (404) y peticiones mal formadas.

### Próximos pasos

- [ ] Migraciones con Flyway.
- [ ] Spring Security con roles USER y ADMIN.
- [ ] Paginación y filtros.
- [ ] Definir y cubrir conflictos de negocio que requieran respuestas 409.
