package org.javanibal.quiz.service;

import org.javanibal.quiz.dto.RespuestaRequest;
import org.javanibal.quiz.dto.RespuestaResponse;
import org.javanibal.quiz.exception.ResourceNotFoundException;
import org.javanibal.quiz.mapper.RespuestaMapper;
import org.javanibal.quiz.model.Pregunta;
import org.javanibal.quiz.model.Respuesta;
import org.javanibal.quiz.repository.PreguntaRepository;
import org.javanibal.quiz.repository.RespuestaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final PreguntaRepository preguntaRepository;
    private final RespuestaMapper respuestaMapper;

    public RespuestaService(RespuestaRepository respuestaRepository,
                            PreguntaRepository preguntaRepository,
                            RespuestaMapper respuestaMapper) {
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.respuestaMapper = respuestaMapper;
    }

    @Transactional(readOnly = true)
    public List<RespuestaResponse> findAll() {
        return respuestaRepository.findAll().stream()
                .map(respuestaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public RespuestaResponse findById(Integer id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada: " + id));
        return respuestaMapper.toResponse(respuesta);
    }

    @Transactional
    public RespuestaResponse create(RespuestaRequest request, Integer preguntaId) {
        Pregunta pregunta = preguntaRepository.findById(preguntaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada: " + preguntaId));

        Respuesta respuesta = respuestaMapper.toEntity(request, pregunta);
        Respuesta guardada = respuestaRepository.save(respuesta);
        return respuestaMapper.toResponse(guardada);
    }

    @Transactional
    public RespuestaResponse update(Integer id, RespuestaRequest request) {
        Respuesta existente = respuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada: " + id));

        existente.setTexto(request.getTexto());
        existente.setOpcion(request.getOpcion());
        existente.setEsCorrecta(request.isEsCorrecta());

        Respuesta guardada = respuestaRepository.save(existente);
        return respuestaMapper.toResponse(guardada);
    }

    @Transactional
    public void delete(Integer id) {
        if (!respuestaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Respuesta no encontrada: " + id);
        }
        respuestaRepository.deleteById(id);
    }
}