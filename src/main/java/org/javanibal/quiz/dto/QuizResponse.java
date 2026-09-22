package org.javanibal.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javanibal.quiz.enums.Categoria;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Datos de un quiz devueltos por la API")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuizResponse {

    @Schema(description = "Identificador único del quiz", example = "1")
    private Integer id;

    @Schema(description = "Título del quiz", example = "Fundamentos de Java")
    private String titulo;

    @Schema(description = "Categoría del quiz", example = "FUNDAMENTOS")
    private Categoria categoria;

    @Schema(description = "Lista de preguntas del quiz (sin respuestas)")
    private List<PreguntaResponse> preguntas = new ArrayList<>();
}