package com.br.deliveryrobot;

import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.br.deliveryrobot.entity.Customer;
import com.br.deliveryrobot.repository.CustomerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class DroneFeederApplicationTests extends AbstractContainerBaseTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private MockMvc mockMvc;

  // given/when/then format - BDD style
  @Test
  public void givenCustomers_whenGetAllCustomers_thenListOfStudents() throws Exception {

    // given - setup or precondition
    List<Customer> customers = List.of(
        Customer.builder().firstName("Bruce").lastName("Wayne").email("eusoubatman@bol.com")
            .address("Batcaverna").build(),
        Customer.builder().firstName("Clark").lastName("Kent").email("clarknlois@gmail.com")
            .address("Smallville").build());
    this.customerRepository.saveAll(customers);

    // when -action
    ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"));

    // then - verify the output
    response.andExpect(MockMvcResultMatchers.status().isOk());
    response
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(customers.size())));

  }

}
