package com.br.deliveryrobot.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_deliverydrone")
public class Deliverydrone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String nickname;
  private double latitude;
  private double longitude;

  @OneToMany(mappedBy = "deliverydrone")
  private Set<Order> orders;

}
