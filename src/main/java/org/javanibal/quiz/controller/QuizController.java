package org.javanibal.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.javanibal.quiz.dto.QuizRequest;
import org.javanibal.quiz.dto.QuizResponse;
import org.javanibal.quiz.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Operation(summary = "Obtener todos los quizzes")
    @ApiResponse(responseCode = "200", description = "Quizzes obtenidos correctamente")
    @GetMapping
    public List<QuizResponse> getAll() {
        return quizService.findAll();
    }

    @Operation(summary = "Obtener un quiz por su ID")
    @ApiResponse(responseCode = "200", description = "Quiz encontrado")
    @ApiResponse(responseCode = "404", description = "Quiz no encontrado")
    @GetMapping("/{id}")
    public QuizResponse getById(@PathVariable Integer id) {
        return quizService.findById(id);
    }

    @Operation(summary = "Crear un nuevo quiz (con sus preguntas y respuestas)")
    @ApiResponse(responseCode = "200", description = "Quiz creado correctamente")
    @PostMapping
    public QuizResponse create(@Valid @RequestBody QuizRequest request) {
        return quizService.create(request);
    }

    @Operation(summary = "Actualizar un quiz")
    @ApiResponse(responseCode = "200", description = "Quiz actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Quiz no encontrado")
    @PutMapping("/{id}")
    public QuizResponse update(@PathVariable Integer id,
                               @Valid @RequestBody QuizRequest request) {
        return quizService.update(id, request);
    }

    @Operation(summary = "Eliminar un quiz y todas sus preguntas y respuestas")
    @ApiResponse(responseCode = "204", description = "Quiz eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Quiz no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}