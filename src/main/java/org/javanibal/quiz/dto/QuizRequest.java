package org.javanibal.quiz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javanibal.quiz.enums.Categoria;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Datos para crear o actualizar un quiz")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuizRequest {

    @Schema(description = "Título del quiz", example = "Fundamentos de Java")
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @Schema(description = "Categoría del quiz", example = "FUNDAMENTOS")
    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;

    @Schema(description = "Lista de preguntas del quiz")
    private List<PreguntaRequest> preguntas = new ArrayList<>();
}