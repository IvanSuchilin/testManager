package com.example.testmanager.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class NotFounElementException extends BaseException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;
    public NotFounElementException(String massage, String reason, LocalDateTime timestamp) {
        super(massage, reason, timestamp);
    }
}
