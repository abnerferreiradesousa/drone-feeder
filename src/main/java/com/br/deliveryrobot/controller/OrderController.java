package com.br.deliveryrobot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.service.OrderService;

public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order registerOrder(@RequestBody Order order) {
    return this.orderService.registerOrder(order);
  }

  @GetMapping("/{orderId}")
  @ResponseStatus(HttpStatus.OK)
  public Order getOrderById(@PathVariable(required = true) long orderId) {
    return this.orderService.getOrderById(orderId);
  }

  @PutMapping("/{orderId}")
  @ResponseStatus(HttpStatus.OK)
  public Order updateOrder(@PathVariable(required = true) long orderId, @RequestBody Order order) {
    return this.orderService.updateOrder(orderId, order);
  }

  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeOrder(@PathVariable(required = true) long orderId) {
    this.orderService.deleteOrder(orderId);
  }
}