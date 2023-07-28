package com.br.deliveryrobot;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {

  static final MySQLContainer MY_SQL_CONTAINER;

  static {
    MY_SQL_CONTAINER = new MySQLContainer<>("mysql:5.7");

    MY_SQL_CONTAINER.start();
  }

}
