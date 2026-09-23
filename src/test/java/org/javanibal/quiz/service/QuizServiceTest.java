package org.javanibal.quiz.service;

import org.javanibal.quiz.dto.QuizResponse;
import org.javanibal.quiz.exception.ResourceNotFoundException;
import org.javanibal.quiz.mapper.QuizMapper;
import org.javanibal.quiz.model.Quiz;
import org.javanibal.quiz.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuizMapper quizMapper;

    @InjectMocks
    private QuizService quizService;

    @Test
    void findAllReturnsMappedResponses() {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        quiz.setTitulo("Backend fundamentals");

        QuizResponse response = new QuizResponse();
        response.setId(1);
        response.setTitulo("Backend fundamentals");

        when(quizRepository.findAll()).thenReturn(List.of(quiz));
        when(quizMapper.toResponse(quiz)).thenReturn(response);

        List<QuizResponse> result = quizService.findAll();

        assertEquals(1, result.size());
        assertEquals("Backend fundamentals", result.getFirst().getTitulo());
        verify(quizRepository).findAll();
        verify(quizMapper).toResponse(quiz);
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(quizRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> quizService.findById(99));
    }

    @Test
    void deleteThrowsWhenMissing() {
        when(quizRepository.existsById(42)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> quizService.delete(42));
    }

    @Test
    void deleteDelegatesToRepositoryWhenExists() {
        when(quizRepository.existsById(42)).thenReturn(true);

        quizService.delete(42);

        verify(quizRepository).deleteById(42);
    }
}