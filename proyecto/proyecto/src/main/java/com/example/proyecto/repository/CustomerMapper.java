package com.example.proyecto.repository;


import com.example.proyecto.model.CustomerDTO;
import com.example.proyecto.model.entity.CustomerEntity;

public interface CustomerMapper {

    CustomerDTO customerConvertToDTO(CustomerEntity customerEntity);

    CustomerEntity customerConvertToEntity (CustomerDTO customerDTO);

}
