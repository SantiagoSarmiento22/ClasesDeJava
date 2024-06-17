package com.example.proyecto.mapper;

import com.example.proyecto.model.CustomerDTO;
import com.example.proyecto.model.entity.CustomerEntity;
import com.example.proyecto.repository.CustomerMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper{

    @Override
    public CustomerDTO customerConvertToDTO(CustomerEntity customerEntity){
        return new CustomerDTO(customerEntity.getDocument(), customerEntity.getName(), customerEntity.getEmail(), customerEntity.getPhone(), customerEntity.getDelivery_address());

    }
    @Override
    public CustomerEntity customerConvertToEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setDocument(customerDTO.getDocument());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setDelivery_address(customerDTO.getDelivery_address());
        return customerEntity;
    }

}


