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

  public Deliverydrone registerDrone(Deliverydrone drone) {
    return this.deliverydroneRepository.save(drone);
  }

  public Deliverydrone getDroneById(long droneId) {
    Deliverydrone droneSearched = this.deliverydroneRepository.findById(droneId)
        .orElseThrow(() -> new NotFoundException("Não encontrado!"));

    return droneSearched;
  }

  public Deliverydrone updateDrone(long droneId, Deliverydrone drone) {
    Deliverydrone droneSearched = this.getDroneById(droneId);

    droneSearched.setNickname(drone.getNickname());
    droneSearched.setLatitude(drone.getLatitude());
    droneSearched.setLongitude(drone.getLongitude());

    return this.deliverydroneRepository.save(droneSearched);
  }

  public void deleteDrone(long droneId) {
    this.deliverydroneRepository.deleteById(droneId);;
  }

}
