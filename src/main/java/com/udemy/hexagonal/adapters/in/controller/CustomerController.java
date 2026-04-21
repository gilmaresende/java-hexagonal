package com.udemy.hexagonal.adapters.in.controller;

import com.udemy.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import com.udemy.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.udemy.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.udemy.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.udemy.hexagonal.application.ports.out.FindCustomerByIdOutputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;

    private FindCustomerByIdOutputPort findCustomerByIdOutputPort;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        insertCustomerInputPort.insert(customerMapper.toCustomer(customerRequest), customerRequest.getZipCode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id) {
        return findCustomerByIdOutputPort.find(id)
                .map(customer -> ResponseEntity.ok(customerMapper.toCustomerResponse(customer)))
                .orElse(ResponseEntity.notFound().build());
    }
}
