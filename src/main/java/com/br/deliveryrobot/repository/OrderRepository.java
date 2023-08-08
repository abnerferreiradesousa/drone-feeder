package com.br.deliveryrobot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.deliveryrobot.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
