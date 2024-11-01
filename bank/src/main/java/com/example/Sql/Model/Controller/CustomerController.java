package com.example.Sql.Model.Controller;

import com.example.Sql.Model.Customer;
import com.example.Sql.Model.Service.CustomerService;
import com.example.Sql.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    // Register endpoint to create a new customer
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Validated @RequestBody Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }

        Customer savedCustomer = customerService.registerCustomer(customer);
        return new ResponseEntity<>("Customer registered with ID: " + savedCustomer.getId(), HttpStatus.CREATED);
    }

    // Login endpoint to verify email and password
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Customer customer = customerService.findCustomerByEmail(email);
        if (customer != null && customerService.checkPassword(password, customer.getPassword())) {
            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
    }
}
