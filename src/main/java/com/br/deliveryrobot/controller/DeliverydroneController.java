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
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.interfaces.IDeliverydroneService;

@RestController
@RequestMapping("/api/drones")
public class DeliverydroneController {

  @Autowired
  private IDeliverydroneService deliverydroneService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Deliverydrone registerDrone(@RequestBody Deliverydrone drone) {
    return this.deliverydroneService.registerDrone(drone);
  }

  // testar caso de exceção
  @GetMapping("/{droneId}")
  @ResponseStatus(HttpStatus.OK)
  public Deliverydrone getDroneById(@PathVariable(required = true) long droneId) {
    return this.deliverydroneService.getDroneById(droneId);
  }

  @PutMapping("/{droneId}")
  @ResponseStatus(HttpStatus.OK)
  public Deliverydrone updateDrone(@PathVariable(required = true) long droneId,
      @RequestBody Deliverydrone drone) {
    return this.deliverydroneService.updateDrone(droneId, drone);
  }

  @DeleteMapping("/{droneId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeDrone(@PathVariable(required = true) long droneId) {
    this.deliverydroneService.deleteDrone(droneId);
  }

}
