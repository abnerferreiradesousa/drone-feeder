package com.br.deliveryrobot.exceptions;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException() {
    super("Não encontrado!");
  }

}
