package org.javanibal.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javanibal.quiz.enums.Opcion;

@Schema(description = "Datos de una respuesta devueltos por la API")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RespuestaResponse {

    @Schema(description = "Identificador único de la respuesta", example = "1")
    private Integer id;

    @Schema(description = "Texto de la respuesta", example = "Una plantilla para objetos")
    private String texto;

    @Schema(description = "Opción de la respuesta", example = "B")
    private Opcion opcion;

    @Schema(description = "Indica si la respuesta es correcta", example = "true")
    private boolean esCorrecta;

    @Schema(description = "ID de la pregunta a la que pertenece", example = "1")
    private Integer preguntaId;
}