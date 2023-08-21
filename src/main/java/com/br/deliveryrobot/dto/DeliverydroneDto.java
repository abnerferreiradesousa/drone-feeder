package com.br.deliveryrobot.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliverydroneDto {

  private long id;
  @NotBlank
  private String nickname;
  private double latitude;
  private double longitude;

}
