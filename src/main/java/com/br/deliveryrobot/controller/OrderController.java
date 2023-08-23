package com.br.deliveryrobot.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.br.deliveryrobot.dto.OrderDto;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.interfaces.IOrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private IOrderService orderService;

  @PostMapping
  @Transactional
  public ResponseEntity<Order> registerOrder(@RequestBody OrderDto orderToSave,
      UriComponentsBuilder uriBuilder) {
    Order order = this.orderService.registerOrder(orderToSave);
    URI uri = uriBuilder.path("/api/orders/{id}").buildAndExpand(order.getId()).toUri();
    return ResponseEntity.created(uri).body(order);
  }

  @GetMapping("{orderId}")
  public ResponseEntity<Order> getOrderById(@PathVariable(required = true) long orderId) {
    return ResponseEntity.ok(this.orderService.getOrderById(orderId));
  }

  @PutMapping("{orderId}/{droneId}")
  @Transactional
  public ResponseEntity<Order> updateOrder(@PathVariable(required = true) long orderId,
      @PathVariable long droneId, @RequestBody OrderDto order) {
    return ResponseEntity.ok(this.orderService.updateOrder(orderId, droneId, order));
  }

  @PutMapping("{orderId}/readyfordelivery")
  @Transactional
  public ResponseEntity<Order> updateToReadyForDelivery(
      @PathVariable(required = true) long orderId) {
    return ResponseEntity.ok(this.orderService.updateToReadyForDelivery(orderId));
  }

  @PutMapping("{orderId}/outfordelivery")
  @Transactional
  public ResponseEntity<Order> updateToOutforDelivery(@PathVariable(required = true) long orderId) {
    return ResponseEntity.ok(this.orderService.updateToOutforDelivery(orderId));
  }

  @PutMapping("{orderId}/{videoId}/delivered")
  @Transactional
  public ResponseEntity<Order> updateToDelivered(@PathVariable(required = true) long orderId,
      @PathVariable(required = true) long videoId) {
    return ResponseEntity.ok(this.orderService.updateToDelivered(orderId, videoId));
  }

  @DeleteMapping("{orderId}")
  @Transactional
  public ResponseEntity<?> removeOrder(@PathVariable(required = true) long orderId) {
    this.orderService.deleteOrder(orderId);
    return ResponseEntity.noContent().build();
  }
}
