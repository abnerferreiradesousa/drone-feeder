package com.br.deliveryrobot.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.repository.OrderRepository;

public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  public Order registerOrder(Order order) {
    return this.orderRepository.save(order);
  }

  public Order getOrderById(long orderId) {
    Order orderSearched = this.orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Não encontrado"));

    return orderSearched;
  }

  public Order updateOrder(long orderId, Order order) {
    Order orderSearched = this.getOrderById(orderId);

    return this.orderRepository.save(orderSearched);
  }

  public void deleteOrder(long orderId) {
    this.orderRepository.deleteById(orderId);;
  }
}
