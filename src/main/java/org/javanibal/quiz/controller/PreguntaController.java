package org.javanibal.quiz.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.javanibal.quiz.model.Pregunta;
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
    public List<Pregunta> getAll() {
        return preguntaService.findAll();
    }

    @Operation(summary = "Obtener una pregunta por su ID")
    @ApiResponse(responseCode = "200", description = "Pregunta encontrada")
    @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Pregunta> getById(@PathVariable Integer id) {
        return preguntaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva pregunta")
    @ApiResponse(responseCode = "200", description = "Pregunta creada correctamente")
    @PostMapping
    public Pregunta create(@Valid @RequestBody Pregunta pregunta) {
        return preguntaService.save(pregunta);
    }

    @Operation(summary = "Actualizar una pregunta")
    @ApiResponse(responseCode = "200", description = "Pregunta actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Pregunta> update(@PathVariable Integer id, @Valid @RequestBody Pregunta pregunta) {
        return preguntaService.findById(id)
                .map(existing -> {
                    pregunta.setId(id);
                    return ResponseEntity.ok(preguntaService.save(pregunta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una pregunta")
    @ApiResponse(responseCode = "204", description = "Pregunta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        preguntaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
