package com.example.testmanager.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({NotFounElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundException(
            final NotFounElementException e) {
        log.debug("Возникла ошибка {},", e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", e.getReason(),
                "status", "NOT_FOUND",
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler({RequestValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRequestValidationException(
            final RequestValidationException e
    ) {
        log.debug("Возникла ошибка {},", e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", e.getReason(),
                "status", "BAD_REQUEST",
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler({DataAlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleNameAlreadyExistException(
            final DataAlreadyExistException e
    ) {
        log.debug("Возникла ошибка {},", e.getMessage());
        return Map.of(
                "message", e.getMessage(),
                "reason", e.getReason(),
                "status", "CONFLICT",
                "timestamp", LocalDateTime.now().toString()
        );
    }
}
