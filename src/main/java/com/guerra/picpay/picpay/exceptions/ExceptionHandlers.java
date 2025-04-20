package com.guerra.picpay.picpay.exceptions;

import com.guerra.picpay.picpay.DTO.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicateEntry(DataIntegrityViolationException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Already registered user","400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(),"500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

}
