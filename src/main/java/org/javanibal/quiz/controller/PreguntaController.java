package org.javanibal.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.javanibal.quiz.dto.PreguntaRequest;
import org.javanibal.quiz.dto.PreguntaResponse;
import org.javanibal.quiz.service.PreguntaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
@CrossOrigin(origins = "*")
public class PreguntaController {

    private final PreguntaService preguntaService;

    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @Operation(summary = "Obtener todas las preguntas")
    @ApiResponse(responseCode = "200", description = "Preguntas obtenidas correctamente")
    @GetMapping
    public List<PreguntaResponse> getAll() {
        return preguntaService.findAll();
    }

    @Operation(summary = "Obtener una pregunta por su ID")
    @ApiResponse(responseCode = "200", description = "Pregunta encontrada")
    @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    @GetMapping("/{id}")
    public PreguntaResponse getById(@PathVariable Integer id) {
        return preguntaService.findById(id);
    }

    @Operation(summary = "Crear una nueva pregunta para un quiz")
    @ApiResponse(responseCode = "200", description = "Pregunta creada correctamente")
    @ApiResponse(responseCode = "404", description = "Quiz no encontrado")
    @PostMapping("/quiz/{quizId}")
    public PreguntaResponse create(@PathVariable Integer quizId,
                                   @Valid @RequestBody PreguntaRequest request) {
        return preguntaService.create(request, quizId);
    }

    @Operation(summary = "Actualizar una pregunta")
    @ApiResponse(responseCode = "200", description = "Pregunta actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    @PutMapping("/{id}")
    public PreguntaResponse update(@PathVariable Integer id,
                                   @Valid @RequestBody PreguntaRequest request) {
        return preguntaService.update(id, request);
    }

    @Operation(summary = "Eliminar una pregunta")
    @ApiResponse(responseCode = "204", description = "Pregunta eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        preguntaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}