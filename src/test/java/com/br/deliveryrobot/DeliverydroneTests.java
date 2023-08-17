package com.br.deliveryrobot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class DeliverydroneTests extends AbstractContainerBaseTest {


  private static final int NONEXISTENT_ON_DATABASE = 999;

  @Autowired
  private MockMvc mockMvc;

  // given/when/then format - BDD style

  @Test
  @Order(1)
  public void givenDrone_whenInsertDrone_thenReturnDroneInserted() throws Exception {
    // given - setup or precondition
    Deliverydrone drone = Deliverydrone.builder().nickname("Exterminador do Futuro")
        .latitude(51.5072).longitude(0.1276).build();

    // when -action
    ResultActions response = this.mockMvc
        .perform(MockMvcRequestBuilders.post("/api/drones").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)));

    // then - verify the output
    response.andExpect(status().isCreated());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.nickname", is(String.valueOf("Exterminador do Futuro"))));
    response.andExpect(jsonPath("$.latitude", is(Double.valueOf(51.5072))));
    response.andExpect(jsonPath("$.longitude", is(Double.valueOf(0.1276))));
  }

  @Test
  @Order(2)
  public void givenDrone_whenUpdateDrone_thenReturnDroneUpdated() throws Exception {
    // given - setup or precondition
    Deliverydrone drone =
        Deliverydrone.builder().nickname("Wall-E").latitude(3.7282).longitude(38.6619).build();

    // when -action
    ResultActions response = this.mockMvc
        .perform(MockMvcRequestBuilders.put("/api/drones/1").contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)));

    // then - verify the output
    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.nickname", is(String.valueOf("Wall-E"))));
    response.andExpect(jsonPath("$.latitude", is(Double.valueOf(3.7282))));
    response.andExpect(jsonPath("$.longitude", is(Double.valueOf(38.6619))));
  }

  @Test
  @Order(3)
  public void givenDrone_whenGetDroneById_thenReturnDrone() throws Exception {
    // when -action
    ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/drones/1"));

    // then - verify the output
    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.nickname", is(String.valueOf("Wall-E"))));
    response.andExpect(jsonPath("$.latitude", is(Double.valueOf(3.7282))));
    response.andExpect(jsonPath("$.longitude", is(Double.valueOf(38.6619))));
  }

  @Test
  @Order(4)
  public void givenDrone_whenDeleteDroneById_thenReturnStatusNoContent() throws Exception {
    // when -action
    ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/drones/1"));
    // then - verify the output
    response.andExpect(status().isNoContent());
    response.andExpect(result -> {
      assertThat(result.getResponse().getContentType()).isNull();
      assertThat(result.getResponse().getContentLength()).isZero();
      assertThat(result.getResponse().getContentAsString()).isEmpty();
    });
  }

  @Test
  public void notGivenDrone_whenGetDroneById_thenReturnNotFound() throws Exception {
    ResultActions response =
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/drones/" + NONEXISTENT_ON_DATABASE));

    response.andExpect(status().isNotFound());
    response.andExpect(jsonPath("$.message", is("Drone não encontrado!")));
    response.andExpect(jsonPath("$.status", is(HttpStatus.NOT_FOUND.value())));
  }

}
