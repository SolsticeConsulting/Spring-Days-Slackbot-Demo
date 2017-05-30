package com.solstice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Justin Baumgartner on 5/21/17.
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService service;

    // Demonstrates new test method parameters.
    // In this example, using SpringExtension,
    // you can Autowire dependencies directly into a test method
    @Test
    void customerListShouldReturnFromService(@Autowired MockMvc mockMvc) throws Exception {
        when(service.getCustomers()).thenReturn(getMockCustomers());

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[1].id", is("2")));
    }

    @Test
    void customerShouldReturnUsingUsernameFromService(@Autowired MockMvc mockMvc) throws Exception {
        Customer mockCustomer = getMockCustomers().get(0);
        when(service.getCustomerByUsername("testname1")).thenReturn(mockCustomer);

        mockMvc.perform(get("/customers/testname1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")));
    }

    // Demonstrates using multiple extensions in the same test class
    @Test
    void usingMultipleExtensions(@Mock Customer customer) {
        when(customer.getFirstName()).thenReturn("Mocker");
        assertEquals(customer.getFirstName(), "Mocker");
    }

    private ArrayList<Customer> getMockCustomers() {
        Customer customer1 = new Customer("1", "testname1", "pass1234", "testname");
        Customer customer2 = new Customer("2", "testname2", "password", "testing");
        return new ArrayList<>(Arrays.asList(customer1, customer2));
    }

}
