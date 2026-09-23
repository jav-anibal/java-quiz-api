package org.javanibal.quiz.mapper;

import org.javanibal.quiz.dto.PreguntaRequest;
import org.javanibal.quiz.dto.PreguntaResponse;
import org.javanibal.quiz.dto.QuizRequest;
import org.javanibal.quiz.dto.QuizResponse;
import org.javanibal.quiz.dto.RespuestaRequest;
import org.javanibal.quiz.model.Pregunta;
import org.javanibal.quiz.model.Quiz;
import org.javanibal.quiz.model.Respuesta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizMapper {

    private final PreguntaMapper preguntaMapper;

    public QuizMapper(PreguntaMapper preguntaMapper) {
        this.preguntaMapper = preguntaMapper;
    }

    /**
     * Entidad -> DTO. Devuelve el quiz con sus preguntas (sin respuestas anidadas).
     */
    public QuizResponse toResponse(Quiz quiz) {
        if (quiz == null) {
            return null;
        }
        QuizResponse dto = new QuizResponse();
        dto.setId(quiz.getId());
        dto.setTitulo(quiz.getTitulo());
        dto.setCategoria(quiz.getCategoria());

        List<PreguntaResponse> preguntas = quiz.getPreguntaList().stream()
                .map(preguntaMapper::toResponse)
                .toList();
        dto.setPreguntas(preguntas);

        return dto;
    }

    /**
     * DTO -> Entidad. NO construye las preguntas ni respuestas.
     * El servicio se encarga de construir el árbol completo con los mappers correspondientes.
     */
    public Quiz toEntity(QuizRequest dto) {
        if (dto == null) {
            return null;
        }
        Quiz quiz = new Quiz();
        quiz.setTitulo(dto.getTitulo());
        quiz.setCategoria(dto.getCategoria());
        return quiz;
    }

    /**
     * Helper para el servicio: construye una Pregunta a partir de un PreguntaRequest,
     * incluyendo sus respuestas, y la asocia al quiz indicado.
     */
    public Pregunta buildPregunta(PreguntaRequest dto, Quiz quiz) {
        Pregunta pregunta = preguntaMapper.toEntity(dto, quiz);
        if (dto.getRespuestas() != null) {
            for (RespuestaRequest r : dto.getRespuestas()) {
                Respuesta respuesta = new Respuesta();
                respuesta.setTexto(r.getTexto());
                respuesta.setOpcion(r.getOpcion());
                respuesta.setEsCorrecta(r.isEsCorrecta());
                respuesta.setPregunta(pregunta);
                pregunta.getRespuestaList().add(respuesta);
            }
        }
        return pregunta;
    }
}