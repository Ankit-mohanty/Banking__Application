package com.jtbank.backend.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(NoResourceFoundException.class)
    public ProblemDetail handler(NoResourceFoundException e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail handler(RuntimeException e) {
        var problemDetail = ProblemDetail.forStatus(400);
        problemDetail.setTitle("Not found");
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handler(HttpRequestMethodNotSupportedException e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(405), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handler(MethodArgumentNotValidException e) {
        var details = new StringJoiner(", ");
        e.getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var message = error.getDefaultMessage();
            details.add(fieldName + ": " + message);
        });

        var problemDetail = ProblemDetail.forStatus(422);
        problemDetail.setTitle("Invalid data");
        problemDetail.setDetail(details.toString());

        return problemDetail;
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handler(Exception e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
    }

}
