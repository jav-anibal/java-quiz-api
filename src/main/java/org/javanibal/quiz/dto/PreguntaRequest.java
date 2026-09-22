package org.javanibal.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Datos para crear o actualizar una pregunta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PreguntaRequest {

    @Schema(description = "Enunciado de la pregunta", example = "¿Qué es una clase en Java?")
    @NotBlank(message = "El enunciado es obligatorio")
    private String enunciado;

    @Schema(description = "ID del quiz al que pertenece la pregunta", example = "1")
    private Integer quizId;

    @Schema(description = "Lista de respuestas de la pregunta")
    private List<RespuestaRequest> respuestas = new ArrayList<>();
}