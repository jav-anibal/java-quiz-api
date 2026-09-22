package org.javanibal.quiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.javanibal.quiz.enums.Opcion;

@Schema(description = "Respuesta asociada a una pregunta")
@Entity
@Table(name = "respuesta")

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la respuesta")
    private Integer id;
    @Schema(description = "Texto de la respuesta")
    @NotBlank(message = "El texto de la respuesta es obligatorio")
    private String texto;

    @Schema(description = "Opción de la respuesta")
    @NotNull(message = "La opción es obligatoria")
    @Enumerated(EnumType.STRING)
    private Opcion opcion;
    @Schema(description = "Indica si la respuesta es correcta")
    private boolean esCorrecta;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    @JsonBackReference
    private Pregunta pregunta;

}
