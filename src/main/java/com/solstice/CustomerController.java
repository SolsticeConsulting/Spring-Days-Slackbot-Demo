package com.solstice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Justin Baumgartner on 5/18/17.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<Customer> fetchCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{customerUsername}")
    Customer fetchCustomer(@PathVariable String customerUsername) {
        return customerService.getCustomerByUsername(customerUsername);
    }

}
