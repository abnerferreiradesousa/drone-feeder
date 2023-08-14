package com.br.deliveryrobot;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.br.deliveryrobot.entity.Order;
import com.br.deliveryrobot.enums.DeliveryStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class OrderTests extends AbstractContainerBaseTest {

  @Autowired
  private MockMvc mockMvc;

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
    response.andExpect(jsonPath("$.boughtAt", notNullValue()));
  }

  @Test
  @org.junit.jupiter.api.Order(3)
  void givenOrder_whenUpdateToReadyForDelivery_thenReturnOrderUpdated() throws Exception {
    ResultActions response =
        mockMvc.perform(MockMvcRequestBuilders.put("/api/orders/1/readyfordelivery"));

    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.PRONTO_PARA_ENTREGA.toString())));
    response.andExpect(jsonPath("$.readyForDeliveryAt", notNullValue()));
  }

  @Test
  @org.junit.jupiter.api.Order(4)
  void givenOrder_whenUpdateToOutForDelivery_thenReturnOrderUpdated() throws Exception {
    ResultActions response =
        mockMvc.perform(MockMvcRequestBuilders.put("/api/orders/1/outfordelivery"));

    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.status", is(DeliveryStatus.EM_TRANSITO.toString())));
    response.andExpect(jsonPath("$.outForDeliveryAt", notNullValue()));
  }

  @Test
  @org.junit.jupiter.api.Order(5)
  void givenOrder_whenDeleteOrder_thenReturnNoContent() throws Exception {
    ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/orders/1"));

    response.andExpect(status().isNoContent());
  }

}
