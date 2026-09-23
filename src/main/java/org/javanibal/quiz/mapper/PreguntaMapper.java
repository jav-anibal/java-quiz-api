package org.javanibal.quiz.mapper;

import org.javanibal.quiz.dto.PreguntaRequest;
import org.javanibal.quiz.dto.PreguntaResponse;
import org.javanibal.quiz.dto.RespuestaResponse;
import org.javanibal.quiz.model.Pregunta;
import org.javanibal.quiz.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PreguntaMapper {

    private final RespuestaMapper respuestaMapper;

    public PreguntaMapper(RespuestaMapper respuestaMapper) {
        this.respuestaMapper = respuestaMapper;
    }

    /**
     * Entidad -> DTO (para devolver al cliente).
     */
    public PreguntaResponse toResponse(Pregunta pregunta) {
        if (pregunta == null) {
            return null;
        }
        PreguntaResponse dto = new PreguntaResponse();
        dto.setId(pregunta.getId());
        dto.setEnunciado(pregunta.getEnunciado());

        Quiz quiz = pregunta.getQuiz();
        if (quiz != null) {
            dto.setQuizId(quiz.getId());
            dto.setQuizTitulo(quiz.getTitulo());
        }

        List<RespuestaResponse> respuestas = pregunta.getRespuestaList().stream()
                .map(respuestaMapper::toResponse)
                .toList();
        dto.setRespuestas(respuestas);

        return dto;
    }

    /**
     * DTO -> Entidad (para guardar en BD).
     * No asigna el quiz; eso lo hace el servicio que conoce a la entidad padre.
     * Tampoco asigna respuestas aquí; el servicio las añade después.
     */
    public Pregunta toEntity(PreguntaRequest dto, Quiz quiz) {
        if (dto == null) {
            return null;
        }
        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado(dto.getEnunciado());
        pregunta.setQuiz(quiz);
        return pregunta;
    }
}