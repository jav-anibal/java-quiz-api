package org.javanibal.quiz.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.javanibal.quiz.enums.Categoria;

import java.util.ArrayList;
import java.util.List;


@Schema(description = "Quiz compuesto por un título, una categoría y sus preguntas")
@Entity
@Table(name="quiz")

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del quiz")
    private Integer id;
    @Schema(description = "Título del quiz")
    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    @Schema(description = "Categoría del quiz")
    @NotNull(message = "La categoría es obligatoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pregunta> preguntaList = new ArrayList<>();




}
