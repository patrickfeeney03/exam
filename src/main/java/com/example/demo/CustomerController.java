package com.example.demo;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.Exceptions.DuplicateCustomerIdException;
import com.example.demo.Exceptions.InvalidCustomerDataException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody @Valid Customer customer) throws DuplicateCustomerIdException, InvalidCustomerDataException {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}