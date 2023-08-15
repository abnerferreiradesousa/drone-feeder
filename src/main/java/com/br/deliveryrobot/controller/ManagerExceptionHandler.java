package com.br.deliveryrobot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.br.deliveryrobot.exceptions.NotFoundException;

// @RestControllerAdvice
public class ManagerExceptionHandler {

  // private Map<String, String> modelError;
  //
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<String> handlerNotFound(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  // @ExceptionHandler({RuntimeException.class})
  // public ResponseEntity<Map<String, String>> handlerInternalServerError(RuntimeException ex) {
  // return ResponseEntity.status(HttpStatus.NOT_FOUND).body();
  // }

}
