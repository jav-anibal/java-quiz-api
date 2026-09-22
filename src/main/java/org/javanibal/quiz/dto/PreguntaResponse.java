package org.javanibal.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Datos de una pregunta devueltos por la API")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PreguntaResponse {

    @Schema(description = "Identificador único de la pregunta", example = "1")
    private Integer id;

    @Schema(description = "Enunciado de la pregunta", example = "¿Qué es una clase en Java?")
    private String enunciado;

    @Schema(description = "ID del quiz al que pertenece", example = "1")
    private Integer quizId;

    @Schema(description = "Título del quiz al que pertenece", example = "Fundamentos de Java")
    private String quizTitulo;

    @Schema(description = "Lista de respuestas de la pregunta")
    private List<RespuestaResponse> respuestas = new ArrayList<>();
}