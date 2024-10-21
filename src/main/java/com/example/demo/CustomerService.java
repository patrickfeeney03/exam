package com.example.demo;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.Exceptions.DuplicateCustomerIdException;
import com.example.demo.Exceptions.InvalidCustomerDataException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final List<Customer> customers;

    public CustomerService(CustomerRepository customerRepository, List<Customer> customers) {
        this.customerRepository = customerRepository;
//        this.customerRepo = customerRepo;
        this.customers = customers;
    }

    public Customer addCustomer(Customer customer) throws DuplicateCustomerIdException, InvalidCustomerDataException {
        if (customerRepository.findByCustomerId(customer.getCustomerId()) != null) {
            throw new DuplicateCustomerIdException(customer.getCustomerId());
        }
        var bornIn = LocalDate.parse(customer.getDateOfBirth());
        if (bornIn.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidCustomerDataException();
        }
        System.out.println();

        customerRepository.save(customer);
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAll() {
        return customers;
    }

    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        Customer foundCustomer = customers.stream().filter(cust -> cust.getCustomerId() == id).findFirst().orElse(null);
        if (foundCustomer == null) {
            throw new CustomerNotFoundException(id);
        }
        return foundCustomer;
    }
}