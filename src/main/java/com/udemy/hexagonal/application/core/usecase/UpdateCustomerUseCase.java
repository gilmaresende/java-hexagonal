package com.udemy.hexagonal.application.core.usecase;

import com.udemy.hexagonal.application.core.domain.Address;
import com.udemy.hexagonal.application.core.domain.Customer;
import com.udemy.hexagonal.application.ports.in.UpdateCustomerInputPort;
import com.udemy.hexagonal.application.ports.out.FindAddressByZipCoreOutputPort;
import com.udemy.hexagonal.application.ports.out.UpdateCustomerOutputPort;

public class UpdateCustomerUseCase implements UpdateCustomerInputPort {

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    private final FindAddressByZipCoreOutputPort findAddressByZipCoreOutputPort;

    private final UpdateCustomerOutputPort updateCustomerOutputPort;

    public UpdateCustomerUseCase(FindCustomerByIdUseCase findCustomerByIdUseCase, FindAddressByZipCoreOutputPort findAddressByZipCoreOutputPort, UpdateCustomerOutputPort updateCustomerOutputPort) {
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.findAddressByZipCoreOutputPort = findAddressByZipCoreOutputPort;
        this.updateCustomerOutputPort = updateCustomerOutputPort;
    }

    public void update(Customer customer, String zipCode) {
        this.findCustomerByIdUseCase.find(customer.getId());
        Address address = findAddressByZipCoreOutputPort.find(zipCode);
        customer.setAddress(address);
        updateCustomerOutputPort.update(customer);
    }
}
