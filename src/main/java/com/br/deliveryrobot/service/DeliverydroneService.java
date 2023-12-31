package com.br.deliveryrobot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.deliveryrobot.dto.DeliverydroneDto;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.exceptions.NotFoundException;
import com.br.deliveryrobot.interfaces.IDeliverydroneService;
import com.br.deliveryrobot.repository.DeliverydroneRepository;

/**
 * Layer that makes data's validate and communicate with the DAO layer to persist using his methods.
 */
@Service
public class DeliverydroneService implements IDeliverydroneService {

  @Autowired
  private DeliverydroneRepository deliverydroneRepository;

  /**
   * Save a given drone.
   * 
   * @param drone Drone which will be registered.
   * @return The drone that was persisted.
   */
  @Override
  public Deliverydrone registerDrone(DeliverydroneDto drone) {
    Deliverydrone droneVo = Deliverydrone.builder().nickname(drone.getNickname())
        .latitude(drone.getLatitude()).longitude(drone.getLongitude()).build();
    return this.deliverydroneRepository.save(droneVo);
  }

  /**
   * Find drone by id.
   * 
   * @param droneId Drone's id that will be searched.
   * @return If drone exists will return the own drone, otherwise it will throw an exception.
   */
  @Override
  public Deliverydrone getDroneById(long droneId) {
    Deliverydrone droneSearched = this.deliverydroneRepository.findById(droneId)
        .orElseThrow(() -> new NotFoundException("Drone não encontrado!"));

    return droneSearched;
  }

  /**
   * Update data of a given drone that founded using a given id.
   * 
   * @param droneId Drone's id that will be searched.
   * @param drone Object with changes that will be persisted in the founded drone.
   * @return Drone updated with given data.
   */
  @Override
  public Deliverydrone updateDrone(long droneId, DeliverydroneDto drone) {
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
  @Override
  public void deleteDrone(long droneId) {
    this.deliverydroneRepository.deleteById(droneId);;
  }

}
