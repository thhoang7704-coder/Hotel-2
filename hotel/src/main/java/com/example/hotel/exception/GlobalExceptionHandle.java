package com.example.hotel.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.hotel.DTO.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                false, ex.getMessage(), null, null, LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
