package org.javanibal.quiz.service;

import org.javanibal.quiz.model.Quiz;
import org.javanibal.quiz.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizService quizService;

    @Test
    void findAllReturnsRepositoryResults() {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        quiz.setTitulo("Backend fundamentals");

        when(quizRepository.findAll()).thenReturn(List.of(quiz));

        List<Quiz> result = quizService.findAll();

        assertEquals(1, result.size());
        assertEquals("Backend fundamentals", result.getFirst().getTitulo());
        verify(quizRepository).findAll();
    }

    @Test
    void deleteDelegatesToRepository() {
        quizService.delete(42);

        verify(quizRepository).deleteById(42);
    }
}
