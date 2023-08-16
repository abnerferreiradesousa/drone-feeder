package com.br.deliveryrobot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.exceptions.NotFoundException;
import com.br.deliveryrobot.repository.DeliverydroneRepository;

@Service
public class DeliverydroneService {

  @Autowired
  private DeliverydroneRepository deliverydroneRepository;

  /**
   * Create a given drone.
   * 
   * @param drone Drone which will be registered.
   * @return The drone that was persisted.
   */
  public Deliverydrone registerDrone(Deliverydrone drone) {
    return this.deliverydroneRepository.save(drone);
  }

  /**
   * Find drone by id.
   * 
   * @param droneId Drone's id that will be searched.
   * @return If drone exists will return the own drone, otherwise it will throw an exception.
   */
  public Deliverydrone getDroneById(long droneId) {
    Deliverydrone droneSearched = this.deliverydroneRepository.findById(droneId)
        .orElseThrow(() -> new NotFoundException("Não encontrado!"));

    return droneSearched;
  }

  /**
   * Search and update data of a given drone that founded using a given id.
   * 
   * @param droneId Drone's id that will be searched.
   * @param drone Object with changes that will be persisted in the founded drone.
   * @return Drone updated with given data.
   */
  public Deliverydrone updateDrone(long droneId, Deliverydrone drone) {
    Deliverydrone droneSearched = this.getDroneById(droneId);

    droneSearched.setNickname(drone.getNickname());
    droneSearched.setLatitude(drone.getLatitude());
    droneSearched.setLongitude(drone.getLongitude());

    return this.deliverydroneRepository.save(droneSearched);
  }

  /**
   * Delete a drone using a given drone's id.
   * 
   * @param droneId Drone's id that will be searched.
   */
  public void deleteDrone(long droneId) {
    this.deliverydroneRepository.deleteById(droneId);;
  }

}
