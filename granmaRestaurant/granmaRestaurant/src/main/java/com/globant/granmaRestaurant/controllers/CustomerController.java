package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.controllers.IControllerEndpoints.ICustomerPath;
import com.globant.granmaRestaurant.model.DTO.CustomerDTO;
import com.globant.granmaRestaurant.services.IServices.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ICustomerPath.URL_BASE)
public class CustomerController {

        @Autowired
        private ICustomerService customerService;

        @PostMapping()
        public ResponseEntity<CustomerDTO> createCustomer(@RequestBody final CustomerDTO customerRequest) {
            final CustomerDTO resultado = this.customerService.createCustomer(customerRequest);
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        }

        @GetMapping()
        public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
            final List<CustomerDTO> customers = this.customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }

        @GetMapping(ICustomerPath.CUSTOMER_ID_PARAM)
        public ResponseEntity<CustomerDTO> getCustomer(@PathVariable final String document) {
            final CustomerDTO customerResponse = this.customerService.getCustomer(document);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        }

        @DeleteMapping(ICustomerPath.CUSTOMER_ID_PARAM)
        public ResponseEntity<Void> deleteCustomer(@PathVariable final String document) {
            this.customerService.deleteCustomer(document);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PutMapping(ICustomerPath.CUSTOMER_ID_PARAM)
        public ResponseEntity<Void> updateCustomer(@PathVariable final String document, @RequestBody final CustomerDTO customerRequest) {
            this.customerService.updateCustomer(document, customerRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    @GetMapping(ICustomerPath.GET_LISTSORT)
    public ResponseEntity<List<CustomerDTO>> getSortedCustomers(
            @RequestParam(required = false) final String sortBy,
            @RequestParam(required = false, defaultValue = "asc") final String order,
            @RequestParam(required = false) final String document,
            @RequestParam(required = false) final String name) {
        final List<CustomerDTO> customers = this.customerService.getSortedCustomers(sortBy, order, document, name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    }