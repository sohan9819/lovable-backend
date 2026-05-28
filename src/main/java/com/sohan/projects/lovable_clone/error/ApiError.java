package com.sohan.projects.lovable_clone.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiError(
        HttpStatus status,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL) List<ApiFieldError> fieldErrors,
        Instant timestamp
) {
    public ApiError(HttpStatus status, String message){
        this(status, message, null, Instant.now() );
    }
    public ApiError(HttpStatus status, String message, List<ApiFieldError> fieldErrors){
        this(status, message, fieldErrors, Instant.now() );
    }

}
