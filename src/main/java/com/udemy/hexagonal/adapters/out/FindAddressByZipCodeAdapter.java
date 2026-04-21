package com.udemy.hexagonal.adapters.out;

import com.udemy.hexagonal.adapters.out.client.FindAddressByZipCodeClient;
import com.udemy.hexagonal.adapters.out.client.mapper.AddressResponseMapper;
import com.udemy.hexagonal.adapters.out.client.response.AddressResponse;
import com.udemy.hexagonal.application.core.domain.Address;
import com.udemy.hexagonal.application.ports.out.FindAddressByZipCoreOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAddressByZipCodeAdapter implements FindAddressByZipCoreOutputPort {

    @Autowired
    private FindAddressByZipCodeClient findAddressByZipCodeClient;

    @Autowired
    private AddressResponseMapper addressResponseMapper;

    @Override
    public Address find(String zipCode) {
        AddressResponse addressResponse = findAddressByZipCodeClient.find(zipCode);
        return addressResponseMapper.toAddress(addressResponse);
    }
}
