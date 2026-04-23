package com.udemy.hexagonal.adapters.in.controller;

import com.udemy.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import com.udemy.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.udemy.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.udemy.hexagonal.application.core.domain.Customer;
import com.udemy.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import com.udemy.hexagonal.application.ports.in.FindCustomerInputPort;
import com.udemy.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.udemy.hexagonal.application.ports.in.UpdateCustomerInputPort;
import com.udemy.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;

    @Autowired
    private FindCustomerInputPort findCustomerInputPort;

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private DeleteCustomerByIdInputPort deleteCustomerByIdInputPort;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        insertCustomerInputPort.insert(customerMapper.toCustomer(customerRequest), customerRequest.getZipCode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id) {
        Customer customer = findCustomerInputPort.find(id);
        CustomerResponse customerResponse = customerMapper.toCustomerResponse(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setId(id);
        updateCustomerInputPort.update(customer, customerRequest.getZipCode());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteCustomerByIdInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
