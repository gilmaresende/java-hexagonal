package com.udemy.hexagonal.config;

import com.udemy.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import com.udemy.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import com.udemy.hexagonal.application.ports.in.FindCustomerInputPort;
import com.udemy.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerById {

    @Bean
    DeleteCustomerByIdInputPort deleteCustomerByIdInputPort(FindCustomerInputPort findCustomerInputPort, DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort) {
        return new DeleteCustomerByIdUseCase(findCustomerInputPort, deleteCustomerByIdOutputPort);
    }
}
