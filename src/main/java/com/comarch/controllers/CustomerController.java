package com.comarch.controllers;


import com.comarch.models.Customer;
import com.comarch.repositories.CustomerRepository;
import com.comarch.requests.AddCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer AddCustomer(@RequestBody AddCustomerRequest addCustomerRequest){
        Customer customer = new Customer();
        customer.setName(addCustomerRequest.name);
        customer.setSurname(addCustomerRequest.surname);
        customer.setEmail(addCustomerRequest.email);
        customer.setIdentityNumber(addCustomerRequest.identity_number);
        customer.setPhoneNumber(addCustomerRequest.phone_number);

        return customerRepository.saveAndFlush(customer);
    }
}
