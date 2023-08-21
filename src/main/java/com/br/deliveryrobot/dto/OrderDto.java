package com.br.deliveryrobot.dto;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import com.br.deliveryrobot.entity.Customer;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

  @NotBlank
  private double totalPrice;
  @NotBlank
  private int itemsQuantity;
  private Customer customer;
  private Deliverydrone deliverydrone;
  private Set<Product> products;

}
