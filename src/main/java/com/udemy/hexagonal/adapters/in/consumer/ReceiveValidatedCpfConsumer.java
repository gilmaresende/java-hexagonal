package com.udemy.hexagonal.adapters.in.consumer;

import com.udemy.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import com.udemy.hexagonal.adapters.in.consumer.message.CustomerMessage;
import com.udemy.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveValidatedCpfConsumer {

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @KafkaListener(topics = "tp-cpf-validated", groupId = "udemy-kafka")
    public void receive(CustomerMessage message) {
        updateCustomerInputPort.update(customerMessageMapper.toCustomer(message), message.getZipCode());
    }
}
