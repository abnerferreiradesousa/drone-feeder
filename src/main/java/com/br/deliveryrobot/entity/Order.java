package com.br.deliveryrobot.entity;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.br.deliveryrobot.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private DeliveryStatus status;

  @Column(name = "total_price")
  private double totalPrice;

  @Column(name = "items_quantity")
  private int itemsQuantity;

  @Column(name = "bought_at")
  private LocalDateTime boughtAt;

  @Column(name = "delivered_at")
  private LocalDateTime deliveredAt;

  @Column(name = "out_for_delivey_at")
  private LocalDateTime outForDeliveryAt;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "video_id", referencedColumnName = "id")
  private Video video;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "deliverydrone_id")
  private Deliverydrone deliverydrone;

  @ManyToMany
  @JoinTable(name = "t_order_product", joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> products;

}
