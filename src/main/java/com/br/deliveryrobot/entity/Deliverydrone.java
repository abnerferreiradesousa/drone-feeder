package com.br.deliveryrobot.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

  // Por que o fetch lazy não funcionou para resolver o problema de recursão?
  @JsonIgnore
  @OneToMany(mappedBy = "deliverydrone", fetch = FetchType.LAZY)
  private List<Order> orders;

}
