package com.br.deliveryrobot.interfaces;

import com.br.deliveryrobot.dto.DeliverydroneDto;
import com.br.deliveryrobot.entity.Deliverydrone;


public interface IDeliverydroneService {

  /**
   * Save a given drone.
   * 
   * @param drone Drone which will be registered.
   * @return The drone that was persisted.
   */
  Deliverydrone registerDrone(DeliverydroneDto drone);

  /**
   * Find drone by id.
   * 
   * @param droneId Drone's id that will be searched.
   * @return If drone exists will return the own drone, otherwise it will throw an exception.
   */
  Deliverydrone getDroneById(long droneId);

  /**
   * Update data of a given drone that founded using a given id.
   * 
   * @param droneId Drone's id that will be searched.
   * @param drone Object with changes that will be persisted in the founded drone.
   * @return Drone updated with given data.
   */
  Deliverydrone updateDrone(long droneId, DeliverydroneDto drone);

  /**
   * Delete a drone using a given drone's id.
   * 
   * @param droneId Drone's id that will be searched.
   */
  void deleteDrone(long droneId);

}
