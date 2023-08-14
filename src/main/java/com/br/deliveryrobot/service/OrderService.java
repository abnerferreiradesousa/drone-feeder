package com.br.deliveryrobot.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.enums.DeliveryStatus;
import com.br.deliveryrobot.repository.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private VideoService videoService;

  @Autowired
  private DeliverydroneService deliverydroneService;

  public Order registerOrder(Order order) {
    return this.orderRepository.save(order);
  }

  public Order getOrderById(long orderId) {
    Order orderSearched = this.orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    return orderSearched;
  }

  public Order updateOrder(long orderId, long droneId, Order order) {
    Order orderSearched = this.getOrderById(orderId);
    Deliverydrone drone = this.deliverydroneService.getDroneById(droneId);

    orderSearched.setDeliverydrone(drone);
    orderSearched.setTotalPrice(order.getTotalPrice());
    orderSearched.setItemsQuantity(order.getItemsQuantity());

    return this.orderRepository.save(orderSearched);
  }

  // public Order updateHelper(long orderId, DeliveryStatus status) {
  // Order orderSearched = this.getOrderById(orderId);
  // orderSearched.setStatus(st);
  // }

  public Order updateToReadyForDelivery(long orderId) {
    Order orderSearched = this.getOrderById(orderId);

    orderSearched.setReadyForDeliveryAt(new Date());
    orderSearched.setStatus(DeliveryStatus.PRONTO_PARA_ENTREGA);

    return this.orderRepository.save(orderSearched);
  }

  public Order updateToOutforDelivery(long orderId) {
    Order orderSearched = this.getOrderById(orderId);

    orderSearched.setOutForDeliveryAt(new Date());
    orderSearched.setStatus(DeliveryStatus.EM_TRANSITO);

    return this.orderRepository.save(orderSearched);
  }

  public Order updateToDelivered(long orderId, long videoId) {
    Order orderSearched = this.getOrderById(orderId);
    Video video = this.videoService.getById(videoId);

    orderSearched.setDeliveredAt(new Date());
    orderSearched.setStatus(DeliveryStatus.ENTREGUE);
    orderSearched.setVideo(video);

    return this.orderRepository.save(orderSearched);
  }

  public void deleteOrder(long orderId) {
    this.orderRepository.deleteById(orderId);;
  }
}
