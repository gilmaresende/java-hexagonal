package com.udemy.hexagonal.adapters.out;

import com.udemy.hexagonal.adapters.out.repository.CustomerRepository;
import com.udemy.hexagonal.adapters.out.repository.entity.CustomerEntity;
import com.udemy.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import com.udemy.hexagonal.application.core.domain.Customer;
import com.udemy.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

    private final CustomerRepository customerRepository;

    private final CustomerEntityMapper customerEntityMapper;

    public UpdateCustomerAdapter(CustomerRepository customerRepository, CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityMapper = customerEntityMapper;
    }

    @Override
    public void update(Customer customer) {
        CustomerEntity customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }

}
