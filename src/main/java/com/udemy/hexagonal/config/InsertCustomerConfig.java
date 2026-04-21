package com.udemy.hexagonal.config;

import com.udemy.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import com.udemy.hexagonal.adapters.out.InsertCustomerAdapter;
import com.udemy.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            InsertCustomerAdapter insertCustomerAdapter
    ) {
        return new InsertCustomerUseCase(findAddressByZipCodeAdapter, insertCustomerAdapter);
    }
}
