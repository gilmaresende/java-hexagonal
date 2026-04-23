package com.udemy.hexagonal.config;

import com.udemy.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import com.udemy.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import com.udemy.hexagonal.application.ports.in.UpdateCustomerInputPort;
import com.udemy.hexagonal.application.ports.out.FindAddressByZipCoreOutputPort;
import com.udemy.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {

    @Bean
    public UpdateCustomerInputPort updateCustomerInputPort(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByZipCoreOutputPort findAddressByZipCoreOutputPort,
            UpdateCustomerOutputPort updateCustomerOutputPort
    ) {
        return new UpdateCustomerUseCase(findCustomerByIdUseCase, findAddressByZipCoreOutputPort, updateCustomerOutputPort);
    }
}
