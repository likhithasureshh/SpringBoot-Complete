package com.springboot.prod_ready_features.exceptions;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiError {
    String error;
    HttpStatus status;
    LocalDateTime timeStamp;

    public ApiError()
    {
        this.timeStamp = LocalDateTime.now();
    }
    public ApiError(String error, HttpStatus status) {
        this();
        this.error = error;
        this.status = status;
    }
}
