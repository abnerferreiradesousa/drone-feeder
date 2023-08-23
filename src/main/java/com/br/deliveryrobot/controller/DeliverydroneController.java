package com.br.deliveryrobot.controller;

import java.net.URI;
import javax.validation.Valid;
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
import com.br.deliveryrobot.dto.DeliverydroneDto;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.interfaces.IDeliverydroneService;

@RestController
@RequestMapping("/api/drones")
public class DeliverydroneController {

  @Autowired
  private IDeliverydroneService deliverydroneService;

  @PostMapping
  @Transactional
  public ResponseEntity<Deliverydrone> registerDrone(
      @RequestBody @Valid DeliverydroneDto droneToCreate, UriComponentsBuilder uriBuilder) {
    Deliverydrone drone = this.deliverydroneService.registerDrone(droneToCreate);
    URI uri = uriBuilder.path("/api/drones/{id}").buildAndExpand(drone.getId()).toUri();
    return ResponseEntity.created(uri).body(drone);
  }

  @GetMapping("/{droneId}")
  public ResponseEntity<Deliverydrone> getDroneById(@PathVariable(required = true) long droneId) {
    return ResponseEntity.ok(this.deliverydroneService.getDroneById(droneId));
  }

  @PutMapping("/{droneId}")
  @Transactional
  public ResponseEntity<Deliverydrone> updateDrone(@PathVariable(required = true) long droneId,
      @RequestBody @Valid DeliverydroneDto drone) {
    return ResponseEntity.ok(this.deliverydroneService.updateDrone(droneId, drone));
  }

  @DeleteMapping("/{droneId}")
  @Transactional
  public ResponseEntity<?> removeDrone(@PathVariable(required = true) long droneId) {
    this.deliverydroneService.deleteDrone(droneId);
    return ResponseEntity.noContent().build();
  }

}
