package com.br.deliveryrobot.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.deliveryrobot.dto.OrderDto;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.entity.Video;
import com.br.deliveryrobot.enums.DeliveryStatus;
import com.br.deliveryrobot.exceptions.NotFoundException;
import com.br.deliveryrobot.interfaces.IDeliverydroneService;
import com.br.deliveryrobot.interfaces.IOrderService;
import com.br.deliveryrobot.interfaces.IVideoService;
import com.br.deliveryrobot.repository.OrderRepository;

@Service
public class OrderService implements IOrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private IVideoService videoService;

  @Autowired
  private IDeliverydroneService deliverydroneService;

  /**
   * Save a given order on database.
   * 
   * @param order Data that will be persisted.
   * @return The order that was created.
   */
  @Override
  public Order registerOrder(OrderDto order) {
    Order orderVo = Order.builder().totalPrice(order.getTotalPrice())
        .itemsQuantity(order.getItemsQuantity()).status(DeliveryStatus.EM_PREPARACAO).build();
    return this.orderRepository.save(orderVo);
  }

  /**
   * Find order using a given order's id.
   * 
   * @param orderId Order's id that will be searched.
   * @return Order that was founded.
   */
  @Override
  public Order getOrderById(long orderId) {
    Order orderSearched =
        this.orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
    return orderSearched;
  }

  /**
   * Update totalPrice or itemsQuantity or the drone which will deliver the order of a given order
   * that founded using a given id.
   * 
   * @param orderId Order's id that will be searched.
   * @param droneId Drone's id that will be searched to add into the order.
   * @param order Object with changes that will be persisted in the founded drone.
   * @return The order updated.
   */
  @Override
  public Order updateOrder(long orderId, long droneId, OrderDto order) {
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

  /**
   * Update status of order to "ready for delivery" and the exact datatime it was happen.
   * 
   * @param orderId Order's id that will be searched.
   * @return The order updated.
   */
  @Override
  public Order updateToReadyForDelivery(long orderId) {
    Order orderSearched = this.getOrderById(orderId);

    orderSearched.setReadyForDeliveryAt(new Date());
    orderSearched.setStatus(DeliveryStatus.PRONTO_PARA_ENTREGA);

    return this.orderRepository.save(orderSearched);
  }

  /**
   * Update status of order to "out for delivery" and the exact datatime it was happen.
   * 
   * @param orderId Order's id that will be searched.
   * @return The order updated.
   */
  @Override
  public Order updateToOutforDelivery(long orderId) {
    Order orderSearched = this.getOrderById(orderId);

    orderSearched.setOutForDeliveryAt(new Date());
    orderSearched.setStatus(DeliveryStatus.EM_TRANSITO);

    return this.orderRepository.save(orderSearched);
  }

  /**
   * Update status of order to "delivered" and the exact datatime it was happen.
   * 
   * @param orderId Order's id that will be searched.
   * @param videoId The id of the deliver that was recorded.
   * @return The order updated.
   */
  @Override
  public Order updateToDelivered(long orderId, long videoId) {
    Order orderSearched = this.getOrderById(orderId);
    Video video = this.videoService.getById(videoId);

    orderSearched.setDeliveredAt(new Date());
    orderSearched.setStatus(DeliveryStatus.ENTREGUE);
    orderSearched.setVideo(video);

    return this.orderRepository.save(orderSearched);
  }

  /**
   * Delete an order using a given id.
   * 
   * @param orderId Order's id that will be searched.
   */
  @Override
  public void deleteOrder(long orderId) {
    this.orderRepository.deleteById(orderId);;
  }
}
