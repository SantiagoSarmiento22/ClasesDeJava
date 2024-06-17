package com.example.proyecto.service;

import com.example.proyecto.mapper.CustomerMapperImpl;
import com.example.proyecto.model.CustomerDTO;
import com.example.proyecto.model.entity.CustomerEntity;
import com.example.proyecto.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerMapperImpl mapper;
    @Autowired
    CustomerRepository repository;


    //hacer el mapper para que mo salga error
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        CustomerEntity customerEntity = mapper.customerConvertToEntity(customerDTO);
        CustomerEntity savedCustomer = repository.save(customerEntity);
        return mapper.customerConvertToDTO(savedCustomer);



    }

    //{va y busca un customer desde su documento}
    @Override
    public CustomerDTO getByDocument(String document){
        CustomerEntity customerPetition = repository.findByDocument(document);
        return mapper.customerConvertToDTO(customerPetition);
    }

    //se actualiza customer desde su document
    @Override
    public void updateCustomer(String document, CustomerDTO customerRequest){
        CustomerEntity existingCustomer = repository.findByDocument(document);
        if (existingCustomer !=null) {
            existingCustomer.setName(customerRequest.getName());
            existingCustomer.setEmail(customerRequest.getEmail());
            existingCustomer.setPhone(customerRequest.getPhone());
            existingCustomer.setDelivery_address(customerRequest.getDelivery_address());
        }
        repository.save(existingCustomer);
    }
    @Override
    public void deleteCustomer(String document){
        CustomerEntity deleteCustomer = repository.findByDocument(document);
        repository.delete(deleteCustomer);
    }


}
