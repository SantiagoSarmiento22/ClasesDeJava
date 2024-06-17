package com.example.proyecto.controller;

import com.example.proyecto.model.CustomerDTO;
import com.example.proyecto.repository.CustomerRepository;
import com.example.proyecto.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

//inyeccion de dependencias
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

   //crear el customer
    //1. va primero que
    @PostMapping("/createCustomer")
    ResponseEntity<CustomerDTO> createCustomer (@RequestBody CustomerDTO customerDTO){
        CustomerDTO createcustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createcustomer, HttpStatus.CREATED);
    }

    //consultar en cliente o customer por el documento
    //1 se hace al inicio o antes que todo
    @GetMapping("/getCustomer/{document}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String document){
        CustomerDTO getCustomer = customerService.getByDocument(document);
        return new ResponseEntity<>(getCustomer,HttpStatus.OK);
    }

    //Actualizar cliente o customer
    @PutMapping("/updateCustomer/{document}")
    public ResponseEntity<Void> updateCustomer (@PathVariable String document, @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer (document, customerDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteCustomer/{document}")
    public ResponseEntity<Void> deleteCustomer (@PathVariable String document){
        customerService.deleteCustomer (document);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
