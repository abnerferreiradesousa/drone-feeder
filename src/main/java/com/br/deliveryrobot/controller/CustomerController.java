package com.br.deliveryrobot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.br.deliveryrobot.entity.Customer;
import com.br.deliveryrobot.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer create(@RequestBody Customer customer) {
    return this.customerRepository.save(customer);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Customer> getAll() {
    return this.customerRepository.findAll();
  }

}
