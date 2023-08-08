package com.br.deliveryrobot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.deliveryrobot.entity.Deliverydrone;

public interface DeliverydroneRepository extends JpaRepository<Deliverydrone, Long> {
}
