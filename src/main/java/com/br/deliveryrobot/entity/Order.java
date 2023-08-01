package com.br.deliveryrobot.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.br.deliveryrobot.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public @Data class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private DeliveryStatus status;

  @Column(name = "total_price")
  private double totalPrice;

  @Column(name = "items_quantity")
  private int itemsQuantity;

  private byte[] video;

  @Column(name = "bought_at")
  private LocalDateTime boughtAt;

  @Column(name = "delivered_at")
  private LocalDateTime deliveredAt;

  @Column(name = "out_for_delivey_at")
  private LocalDateTime outForDeliveryAt;

}
