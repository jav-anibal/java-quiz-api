package org.javanibal.quiz.service;

import org.javanibal.quiz.dto.PreguntaRequest;
import org.javanibal.quiz.dto.PreguntaResponse;
import org.javanibal.quiz.dto.RespuestaRequest;
import org.javanibal.quiz.exception.ResourceNotFoundException;
import org.javanibal.quiz.mapper.PreguntaMapper;
import org.javanibal.quiz.mapper.RespuestaMapper;
import org.javanibal.quiz.model.Pregunta;
import org.javanibal.quiz.model.Quiz;
import org.javanibal.quiz.model.Respuesta;
import org.javanibal.quiz.repository.PreguntaRepository;
import org.javanibal.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;
    private final QuizRepository quizRepository;
    private final PreguntaMapper preguntaMapper;
    private final RespuestaMapper respuestaMapper;

    public PreguntaService(PreguntaRepository preguntaRepository,
                           QuizRepository quizRepository,
                           PreguntaMapper preguntaMapper,
                           RespuestaMapper respuestaMapper) {
        this.preguntaRepository = preguntaRepository;
        this.quizRepository = quizRepository;
        this.preguntaMapper = preguntaMapper;
        this.respuestaMapper = respuestaMapper;
    }

    @Transactional(readOnly = true)
    public List<PreguntaResponse> findAll() {
        return preguntaRepository.findAll().stream()
                .map(preguntaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PreguntaResponse findById(Integer id) {
        Pregunta pregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada: " + id));
        return preguntaMapper.toResponse(pregunta);
    }

    @Transactional
    public PreguntaResponse create(PreguntaRequest request, Integer quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado: " + quizId));

        Pregunta pregunta = preguntaMapper.toEntity(request, quiz);

        if (request.getRespuestas() != null) {
            for (RespuestaRequest r : request.getRespuestas()) {
                Respuesta respuesta = respuestaMapper.toEntity(r, pregunta);
                pregunta.getRespuestaList().add(respuesta);
            }
        }

        Pregunta guardada = preguntaRepository.save(pregunta);
        return preguntaMapper.toResponse(guardada);
    }

    @Transactional
    public PreguntaResponse update(Integer id, PreguntaRequest request) {
        Pregunta existente = preguntaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada: " + id));

        existente.setEnunciado(request.getEnunciado());

        Pregunta guardada = preguntaRepository.save(existente);
        return preguntaMapper.toResponse(guardada);
    }

    @Transactional
    public void delete(Integer id) {
        if (!preguntaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pregunta no encontrada: " + id);
        }
        preguntaRepository.deleteById(id);
    }
}
