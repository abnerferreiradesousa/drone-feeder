package com.br.deliveryrobot.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice
public class ManagerExceptionHandler {

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<String> handlerDroneNotFound(RuntimeException ex) {

    System.out.println(ex);
    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(ex.getMessage());
  }

}
