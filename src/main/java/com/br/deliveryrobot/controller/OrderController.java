package com.br.deliveryrobot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order registerOrder(@RequestBody Order order) {
    return this.orderService.registerOrder(order);
  }

  // testar caso de exceção
  @GetMapping("{orderId}")
  @ResponseStatus(HttpStatus.OK)
  public Order getOrderById(@PathVariable(required = true) long orderId) {
    return this.orderService.getOrderById(orderId);
  }

  @PutMapping("{orderId}/{droneId}")
  @ResponseStatus(HttpStatus.OK)
  public Order updateOrder(@PathVariable(required = true) long orderId, @PathVariable long droneId,
      @RequestBody Order order) {
    return this.orderService.updateOrder(orderId, droneId, order);
  }

  @PutMapping("{orderId}/readyfordelivery")
  public Order updateToReadyForDelivery(@PathVariable(required = true) long orderId) {
    return this.orderService.updateToReadyForDelivery(orderId);
  }

  @PutMapping("{orderId}/outfordelivery")
  public Order updateToOutforDelivery(@PathVariable(required = true) long orderId) {
    return this.orderService.updateToOutforDelivery(orderId);
  }

  @PutMapping("{orderId}/{videoId}/delivered")
  public Order updateToDelivered(@PathVariable(required = true) long orderId,
      @PathVariable(required = true) long videoId) {
    return this.orderService.updateToDelivered(orderId, videoId);
  }

  @DeleteMapping("{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeOrder(@PathVariable(required = true) long orderId) {
    this.orderService.deleteOrder(orderId);
  }
}
