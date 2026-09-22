package org.javanibal.quiz.controller;


import jakarta.validation.Valid;
import org.javanibal.quiz.model.Quiz;
import org.javanibal.quiz.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
    public List<Quiz>getAll(){
        return quizService.findAll();
    }

    @Operation(summary = "Obtener un quiz por su ID")
    @ApiResponse(responseCode = "200", description = "Quiz encontrado")
    @ApiResponse(responseCode = "404", description = "Quiz no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable Integer id){
        return quizService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @Operation(summary = "Crear un nuevo quiz")
    @ApiResponse(responseCode = "200", description = "Quiz creado correctamente")
    @PostMapping
    public Quiz create(@Valid @RequestBody Quiz quiz){
        return quizService.save(quiz);
    }

    @Operation(summary = "Actualizar un quiz")
    @ApiResponse(responseCode = "200", description = "Quiz actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Quiz no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Quiz> update(@PathVariable Integer id, @Valid @RequestBody Quiz quiz){
        return quizService.findById(id)
                .map(existing -> {
                    quiz.setId(id);
                return ResponseEntity.ok(quizService.save(quiz));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Eliminar un quiz")
    @ApiResponse(responseCode = "204", description = "Quiz eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
