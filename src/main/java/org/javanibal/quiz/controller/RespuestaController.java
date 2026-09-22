package org.javanibal.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.javanibal.quiz.model.Respuesta;
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
    public List<Respuesta> getAll() {
        return respuestaService.findAll();
    }

    @Operation(summary = "Obtener una respuesta por su ID")
    @ApiResponse(responseCode = "200", description = "Respuesta encontrada")
    @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> getById(@PathVariable Integer id) {
        return respuestaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva respuesta")
    @ApiResponse(responseCode = "200", description = "Respuesta creada correctamente")
    @PostMapping
    public Respuesta create(@Valid @RequestBody Respuesta respuesta) {
        return respuestaService.save(respuesta);
    }

    @Operation(summary = "Actualizar una respuesta")
    @ApiResponse(responseCode = "200", description = "Respuesta actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> update(@PathVariable Integer id, @Valid @RequestBody Respuesta respuesta) {
        return respuestaService.findById(id)
                .map(existing -> {
                    respuesta.setId(id);
                    return ResponseEntity.ok(respuestaService.save(respuesta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una respuesta")
    @ApiResponse(responseCode = "204", description = "Respuesta eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        respuestaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
