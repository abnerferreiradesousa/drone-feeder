package com.br.deliveryrobot.interfaces;

import com.br.deliveryrobot.entity.Order;


public interface IOrderService {

  /**
   * Save a given order on database.
   * 
   * @param order Data that will be persisted.
   * @return The order that was created.
   */
  Order registerOrder(Order order);

  /**
   * Find order using a given order's id.
   * 
   * @param orderId Order's id that will be searched.
   * @return Order that was founded.
   */
  Order getOrderById(long orderId);

  /**
   * Update totalPrice or itemsQuantity or the drone which will deliver the order of a given order
   * that founded using a given id.
   * 
   * @param orderId Order's id that will be searched.
   * @param droneId Drone's id that will be searched to add into the order.
   * @param order Object with changes that will be persisted in the founded drone.
   * @return The order updated.
   */
  Order updateOrder(long orderId, long droneId, Order order);

  /**
   * Update status of order to "ready for delivery" and the exact datatime it was happen.
   * 
   * @param orderId Order's id that will be searched.
   * @return The order updated.
   */
  Order updateToReadyForDelivery(long orderId);

  /**
   * Update status of order to "out for delivery" and the exact datatime it was happen.
   * 
   * @param orderId Order's id that will be searched.
   * @return The order updated.
   */
  Order updateToOutforDelivery(long orderId);

  /**
   * Update status of order to "delivered" and the exact datatime it was happen.
   * 
   * @param orderId Order's id that will be searched.
   * @param videoId The id of the deliver that was recorded.
   * @return The order updated.
   */
  Order updateToDelivered(long orderId, long videoId);

  /**
   * Delete an order using a given id.
   * 
   * @param orderId Order's id that will be searched.
   */
  void deleteOrder(long orderId);

}
