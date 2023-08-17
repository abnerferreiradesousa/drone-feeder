package com.br.deliveryrobot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.enums.DeliveryStatus;
import com.br.deliveryrobot.service.DeliverydroneService;
import com.br.deliveryrobot.service.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class OrderTests extends AbstractContainerBaseTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DeliverydroneService deliverydroneService;

  @MockBean
  private VideoService videoService;

  private static final int NONEXISTENT_ON_DATABASE = 999;

  @Test
  @org.junit.jupiter.api.Order(1)
  void givenOrder_whenInsertOrder_thenReturnOrder() throws Exception {
    Order order = Order.builder().totalPrice(12.00).itemsQuantity(4).build();

    ResultActions response = mockMvc
        .perform(MockMvcRequestBuilders.post("/api/orders").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(order)));

    response.andExpect(status().isCreated());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.totalPrice", is(Double.valueOf(12.00))));
    response.andExpect(jsonPath("$.itemsQuantity", is(4)));
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.EM_PREPARACAO.toString())));
    response.andExpect(jsonPath("$.boughtAt", notNullValue()));
  }

  @Test
  @org.junit.jupiter.api.Order(2)
  void givenOrder_whenGetOrderById_thenReturnOrder() throws Exception {
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/1"));

    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.totalPrice", is(Double.valueOf(12.00))));
    response.andExpect(jsonPath("$.itemsQuantity", is(4)));
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.EM_PREPARACAO.toString())));
    response.andExpect(jsonPath("$.boughtAt", notNullValue()));
  }

  @Test
  @org.junit.jupiter.api.Order(3)
  void givenOrder_whenUpdateOrder_thenReturnOrderUpdated() throws Exception {
    Deliverydrone drone =
        Deliverydrone.builder().nickname("Wall-E").latitude(35.142).longitude(27.053).build();

    this.deliverydroneService.registerDrone(drone);

    Order mockOrder = Order.builder().itemsQuantity(25).totalPrice(123.55).build();

    ResultActions response = mockMvc.perform(
        MockMvcRequestBuilders.put("/api/orders/1/1").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(mockOrder)));

    // Should update
    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.totalPrice", is(Double.valueOf(123.55))));
    response.andExpect(jsonPath("$.itemsQuantity", is(25)));

    // Should not update
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.boughtAt", notNullValue()));
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.EM_PREPARACAO.toString())));
  }

  @Test
  @org.junit.jupiter.api.Order(4)
  void givenOrder_whenUpdateToReadyForDelivery_thenReturnOrderUpdated() throws Exception {
    ResultActions response =
        mockMvc.perform(MockMvcRequestBuilders.put("/api/orders/1/readyfordelivery"));

    // Should update
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.PRONTO_PARA_ENTREGA.toString())));
    response.andExpect(jsonPath("$.readyForDeliveryAt", notNullValue()));

    // Should not update
    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.totalPrice", is(Double.valueOf(123.55))));
    response.andExpect(jsonPath("$.itemsQuantity", is(25)));
  }

  @Test
  @org.junit.jupiter.api.Order(5)
  void givenOrder_whenUpdateToOutForDelivery_thenReturnOrderUpdated() throws Exception {
    ResultActions response =
        mockMvc.perform(MockMvcRequestBuilders.put("/api/orders/1/outfordelivery"));

    // Should update
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.EM_TRANSITO.toString())));
    response.andExpect(jsonPath("$.outForDeliveryAt", notNullValue()));

    // Should not update
    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.totalPrice", is(Double.valueOf(123.55))));
    response.andExpect(jsonPath("$.itemsQuantity", is(25)));
  }

  @Test
  @org.junit.jupiter.api.Order(6)
  void givenOrder_whenUpdateToDelivered_thenReturnOrderUpdated() throws Exception {
    MultipartFile file = mock(MultipartFile.class);
    // Video video = Video.builder().name("MeuVideo").data(file.getBytes()).build();

    this.videoService.saveVideo(file);

    ResultActions response =
        mockMvc.perform(MockMvcRequestBuilders.put("/api/orders/1/1/delivered"));

    // Should update
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.ENTREGUE.toString())));
    response.andExpect(jsonPath("$.deliveredAt", notNullValue()));

    // Should not update
    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.totalPrice", is(Double.valueOf(123.55))));
    response.andExpect(jsonPath("$.itemsQuantity", is(25)));
  }

  @Test
  @org.junit.jupiter.api.Order(7)
  void givenOrder_whenDeleteOrder_thenReturnNoContent() throws Exception {
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/orders/1"));

    response.andExpect(status().isNoContent());
    response.andExpect(result -> {
      assertThat(result.getResponse().getContentType()).isNull();
      assertThat(result.getResponse().getContentLength()).isZero();
      assertThat(result.getResponse().getContentAsString()).isBlank();
    });
  }

  @Test
  public void notGivenDrone_whenGetDroneById_thenReturnNotFound() throws Exception {
    ResultActions response =
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/" + NONEXISTENT_ON_DATABASE));

    response.andExpect(status().isNotFound());
    response.andExpect(jsonPath("$.message", is("Não encontrado!")));
    response.andExpect(jsonPath("$.status", is(HttpStatus.NOT_FOUND.value())));
  }

}
