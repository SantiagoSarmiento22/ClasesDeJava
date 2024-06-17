package com.example.proyecto.service;

import com.example.proyecto.model.CustomerDTO;

public interface CustomerService {

    //1. va primero que el customerController para que no salga error
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    //1. cnsulta de cliente, va despues de customer controller
    CustomerDTO getByDocument(String document);

    //se actualiza customer desde su document
    void updateCustomer(String document, CustomerDTO customerRequest);

    void deleteCustomer(String document);
}
