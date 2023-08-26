package com.example.testmanager.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseException extends RuntimeException{
    private final String message;
    private final String reason;
    private final LocalDateTime time;

    public BaseException(String message, String reason, LocalDateTime time) {
        this.message = message;
        this.reason = reason;
        this.time = time;
    }
}
