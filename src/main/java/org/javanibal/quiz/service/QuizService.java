package org.javanibal.quiz.service;

import org.javanibal.quiz.dto.PreguntaRequest;
import org.javanibal.quiz.dto.QuizRequest;
import org.javanibal.quiz.dto.QuizResponse;
import org.javanibal.quiz.exception.ResourceNotFoundException;
import org.javanibal.quiz.mapper.QuizMapper;
import org.javanibal.quiz.model.Pregunta;
import org.javanibal.quiz.model.Quiz;
import org.javanibal.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    public QuizService(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Transactional(readOnly = true)
    public List<QuizResponse> findAll() {
        return quizRepository.findAll().stream()
                .map(quizMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public QuizResponse findById(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado: " + id));
        return quizMapper.toResponse(quiz);
    }

    @Transactional
    public QuizResponse create(QuizRequest request) {
        Quiz quiz = quizMapper.toEntity(request);

        if (request.getPreguntas() != null) {
            for (PreguntaRequest p : request.getPreguntas()) {
                Pregunta pregunta = quizMapper.buildPregunta(p, quiz);
                quiz.getPreguntaList().add(pregunta);
            }
        }

        Quiz guardado = quizRepository.save(quiz);
        return quizMapper.toResponse(guardado);
    }

    @Transactional
    public QuizResponse update(Integer id, QuizRequest request) {
        Quiz existente = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado: " + id));

        existente.setTitulo(request.getTitulo());
        existente.setCategoria(request.getCategoria());

        Quiz guardado = quizRepository.save(existente);
        return quizMapper.toResponse(guardado);
    }

    @Transactional
    public void delete(Integer id) {
        if (!quizRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quiz no encontrado: " + id);
        }
        quizRepository.deleteById(id);
    }
}