package org.javanibal.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

// Este test levanta un Postgres real via Testcontainers para validar que el
// contexto de Spring arranca contra el motor de BD real (no H2). Necesita
// Docker disponible; si no lo hay, se salta en vez de romper el build.
@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class QuizBackendSpringbootApplicationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Test
    void contextLoads() {
    }

}
