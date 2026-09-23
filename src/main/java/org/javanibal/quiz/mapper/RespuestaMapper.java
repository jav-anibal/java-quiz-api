package org.javanibal.quiz.mapper;

import org.javanibal.quiz.dto.RespuestaRequest;
import org.javanibal.quiz.dto.RespuestaResponse;
import org.javanibal.quiz.model.Pregunta;
import org.javanibal.quiz.model.Respuesta;
import org.springframework.stereotype.Component;

@Component
public class RespuestaMapper {

    /**
     * Entidad -> DTO (para devolver al cliente).
     */
    public RespuestaResponse toResponse(Respuesta respuesta) {
        if (respuesta == null) {
            return null;
        }
        RespuestaResponse dto = new RespuestaResponse();
        dto.setId(respuesta.getId());
        dto.setTexto(respuesta.getTexto());
        dto.setOpcion(respuesta.getOpcion());
        dto.setEsCorrecta(respuesta.isEsCorrecta());
        dto.setPreguntaId(respuesta.getPregunta() != null ? respuesta.getPregunta().getId() : null);
        return dto;
    }

    /**
     * DTO -> Entidad (para guardar en BD).
     * No asigna la pregunta; eso lo hace el servicio que conoce a la entidad padre.
     */
    public Respuesta toEntity(RespuestaRequest dto, Pregunta pregunta) {
        if (dto == null) {
            return null;
        }
        Respuesta respuesta = new Respuesta();
        respuesta.setTexto(dto.getTexto());
        respuesta.setOpcion(dto.getOpcion());
        respuesta.setEsCorrecta(dto.isEsCorrecta());
        respuesta.setPregunta(pregunta);
        return respuesta;
    }
}