package com.onetwo.letterservice.common.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onetwo.mailboxcommonconfig.common.exceptions.BadRequestException;
import onetwo.mailboxcommonconfig.common.exceptions.BadResponseException;
import onetwo.mailboxcommonconfig.common.exceptions.TokenValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException e) {
        log.info("BadRequestException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(BadResponseException.class)
    public ResponseEntity<String> badResponseException(BadResponseException e) {
        log.info("BadResponseException", e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<String> tokenValidationException(TokenValidationException e) {
        log.info("TokenValidationException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<String> notFoundResourceException(NotFoundResourceException e) {
        log.info("NotFoundResourceException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.info("HttpMessageNotReadableException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> resourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        log.info("ResourceAlreadyExistsException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
        log.info("ConstraintViolationException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyFullException.class)
    public ResponseEntity<String> resourceAlreadyFullException(ResourceAlreadyFullException e) {
        log.info("ResourceAlreadyFullException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyDeletedException.class)
    public ResponseEntity<String> resourceAlreadyDeletedException(ResourceAlreadyDeletedException e) {
        log.info("ResourceAlreadyDeletedException", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
