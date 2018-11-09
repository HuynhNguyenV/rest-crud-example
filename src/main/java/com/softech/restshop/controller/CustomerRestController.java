package com.softech.restshop.controller;


import com.softech.restshop.domain.Customer;
import com.softech.restshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public List<Customer> getCustomer(){
        return (List<Customer>)repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") String customerId){
        return repository.findById(customerId);
    }

    @PostMapping
    public Customer postCustomer(@RequestBody Customer customer){
        return repository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer putCustomer(@PathVariable("id") String customerId,
                                @RequestBody Customer customer){
        Optional<Customer> optionalCustomer = repository.findById(customerId);

        if (optionalCustomer.isPresent()){
            Customer customer1 = optionalCustomer.get();
            customer1.setName(customer.getName());
            return repository.save(customer1);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String customerId){
        repository.deleteById(customerId);
    }


}
