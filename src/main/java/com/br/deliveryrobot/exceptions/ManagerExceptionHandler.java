package com.br.deliveryrobot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManagerExceptionHandler {

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<ErrorBody> handleNotFound(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorBody(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<ErrorBody> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorBody(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorBody> handleException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
  }

  @ExceptionHandler({Throwable.class})
  public ResponseEntity<ErrorBody> handleThrowable(Throwable ex) {
    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
        .body(new ErrorBody(HttpStatus.BAD_GATEWAY.value(), ex.getMessage()));
  }

}
