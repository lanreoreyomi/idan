package com.idan.gig.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(GigNotFoundException.class)
  public ResponseEntity<String> handleGigNotFoundException(GigNotFoundException ex) {
    logger.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }
  @ExceptionHandler(InvalidJwtTokenException.class)
  public ResponseEntity<String> handleUserNotFoundException(InvalidJwtTokenException ex) {
    logger.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
  }
}
