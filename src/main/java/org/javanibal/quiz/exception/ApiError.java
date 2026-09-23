package org.javanibal.quiz.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        List<String> details
) {
    public ApiError(LocalDateTime timestamp, int status, String error, String message) {
        this(timestamp, status, error, message, List.of());
    }
}
