package org.javanibal.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.javanibal.quiz.dto.RespuestaRequest;
import org.javanibal.quiz.dto.RespuestaResponse;
import org.javanibal.quiz.service.RespuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respuestas")
@CrossOrigin(origins = "*")
public class RespuestaController {

    private final RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @Operation(summary = "Obtener todas las respuestas")
    @ApiResponse(responseCode = "200", description = "Respuestas obtenidas correctamente")
    @GetMapping
    public List<RespuestaResponse> getAll() {
        return respuestaService.findAll();
    }

    @Operation(summary = "Obtener una respuesta por su ID")
    @ApiResponse(responseCode = "200", description = "Respuesta encontrada")
    @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    @GetMapping("/{id}")
    public RespuestaResponse getById(@PathVariable Integer id) {
        return respuestaService.findById(id);
    }

    @Operation(summary = "Crear una nueva respuesta para una pregunta")
    @ApiResponse(responseCode = "200", description = "Respuesta creada correctamente")
    @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    @PostMapping("/pregunta/{preguntaId}")
    public RespuestaResponse create(@PathVariable Integer preguntaId,
                                    @Valid @RequestBody RespuestaRequest request) {
        return respuestaService.create(request, preguntaId);
    }

    @Operation(summary = "Actualizar una respuesta")
    @ApiResponse(responseCode = "200", description = "Respuesta actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    @PutMapping("/{id}")
    public RespuestaResponse update(@PathVariable Integer id,
                                    @Valid @RequestBody RespuestaRequest request) {
        return respuestaService.update(id, request);
    }

    @Operation(summary = "Eliminar una respuesta")
    @ApiResponse(responseCode = "204", description = "Respuesta eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        respuestaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}