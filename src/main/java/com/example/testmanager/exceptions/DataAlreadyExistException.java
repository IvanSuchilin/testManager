package com.example.testmanager.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class DataAlreadyExistException extends BaseException {
    private final HttpStatus status = HttpStatus.CONFLICT;
    public DataAlreadyExistException(String message, String reason, LocalDateTime time) {
        super(message, reason, time);
    }
}
