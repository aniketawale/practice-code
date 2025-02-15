package com.lcwd.hotel.excpetion;


import com.lcwd.hotel.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", exception.getMessage());
        response.put("success", false);
        response.put("status", HttpStatus.NOT_FOUND.value()); // Using numeric status code

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
