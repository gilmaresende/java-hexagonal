package com.udemy.hexagonal.application.core.usecase;


import com.udemy.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import com.udemy.hexagonal.application.ports.in.FindCustomerInputPort;
import com.udemy.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;

public class DeleteCustomerByIdUseCase implements DeleteCustomerByIdInputPort {

    private final FindCustomerInputPort findCustomerInputPort;

    private final DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort;

    public DeleteCustomerByIdUseCase(FindCustomerInputPort findCustomerInputPort, DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort) {
        this.findCustomerInputPort = findCustomerInputPort;
        this.deleteCustomerByIdOutputPort = deleteCustomerByIdOutputPort;
    }

    @Override
    public void delete(String id) {
        findCustomerInputPort.find(id);
        deleteCustomerByIdOutputPort.delete(id);
    }
}
