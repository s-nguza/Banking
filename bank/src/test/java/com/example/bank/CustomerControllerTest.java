package com.example.bank;

import com.example.Sql.Model.Customer;
import com.example.Sql.Repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testRegisterCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setAge(30);
        customer.setEmail("john.doe@example.com");
        customer.setPassword("password123");

        mockMvc.perform(post("/api/customers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Customer registered with ID: 1"));
    }

    @Test
    public void testLoginCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setAge(30);
        customer.setEmail("john.doe@example.com");
        customer.setPassword("password123");
        customerRepository.save(customer);

        mockMvc.perform(post("/api/customers/login")
                        .param("email", "john.doe@example.com")
                        .param("password", "password123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful!"));
    }
}
