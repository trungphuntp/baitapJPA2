package com.baitapjpa.baitapvenhajpa.exception;

import com.baitapjpa.baitapvenhajpa.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(400);
        baseResponse.setMessage("Validation failed");
        baseResponse.setData(errors);
        return ResponseEntity.badRequest().body(baseResponse);
    }
}
