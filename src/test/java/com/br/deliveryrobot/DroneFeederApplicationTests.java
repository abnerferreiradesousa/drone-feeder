package com.br.deliveryrobot;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.br.deliveryrobot.entity.Deliverydrone;
import com.br.deliveryrobot.repository.DeliverydroneRepository;


@SpringBootTest
@AutoConfigureMockMvc
class DroneFeederApplicationTests extends AbstractContainerBaseTest {

  @Autowired
  private DeliverydroneRepository deliverydroneRepository;

  @Autowired
  private MockMvc mockMvc;

  // given/when/then format - BDD style
  @Test
  public void givenDrones_whenGetDroneById_thenReturnDrone() throws Exception {

    // given - setup or precondition
    List<Deliverydrone> deliverydrones = List.of(
        Deliverydrone.builder().nickname("Exterminador do Futuro").latitude(51.5072)
            .longitude(0.1276).build(),
        Deliverydrone.builder().nickname("Wall-E").latitude(3.7282).longitude(38.6619).build());

    this.deliverydroneRepository.saveAll(deliverydrones);

    // First request
    // when -action
    ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/drones/1"));

    // then - verify the output
    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(1))));
    response.andExpect(jsonPath("$.nickname", is(String.valueOf("Exterminador do Futuro"))));
    response.andExpect(jsonPath("$.latitude", is(Double.valueOf(51.5072))));
    response.andExpect(jsonPath("$.longitude", is(Double.valueOf(0.1276))));

    // Second Resquest
    response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/drones/2"));

    response.andExpect(status().isOk());
    response.andExpect(jsonPath("$.id", is(Integer.valueOf(2))));
    response.andExpect(jsonPath("$.nickname", is(String.valueOf("Wall-E"))));
    response.andExpect(jsonPath("$.latitude", is(Double.valueOf(3.7282))));
    response.andExpect(jsonPath("$.longitude", is(Double.valueOf(38.6619))));
  }

}
