package com.solstice;

import ai.api.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Justin Baumgartner on 5/14/17.
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ApiAiClient apiAiClient;

    //api.ai action key names
    private static final String PASSWORD_RESET_ACTION = "password_reset";
    private static final String SECURITY_REQUEST_ACTION = "security_question";


    @Autowired
    CustomerService(CustomerRepository customerRepository, ApiAiClient apiAiClient) {
        this.customerRepository = customerRepository;
        this.apiAiClient = apiAiClient;
    }

    Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    Customer getCustomer(String customerId) {
        return customerRepository.findOne(customerId);
    }

    Customer getCustomerByUsername(String customerUsername) {
        return customerRepository.findByUsername(customerUsername);
    }

    String handleCustomerMessage(String customerId, String message) {
        if (message == null || message.equals("")) {
            return "I cannot help with that request";
        }
        //Fetch the customer from the database based on the customer ID
        Customer customer = getCustomer(customerId);
        //Fetch the primary security question and its applicable answer
        List<SecurityQuestionAnswer> questions = customer.getSecurityQuestionAnswers();
        String question = questions.get(0).getQuestion();
        String answer = questions.get(0).getAnswer().toLowerCase();
        //Seed the Giphy client if we need it
        Future<String> gif = GiphyClient.getFailureGif();
        //Process user message from
        Result result = apiAiClient.processText(message);
        if (result.getAction().equals(PASSWORD_RESET_ACTION)) {
            return MessageFormat.format("Hi {0}, I can help with that! All I need to know is one security question. \n\n{1}", customer.getFirstName(), question);
        } else if (result.getAction().equals(SECURITY_REQUEST_ACTION) || message.toLowerCase().equals(answer)) {
            resetCustomerPassword(customer);
            return MessageFormat.format("Success! Your new password is \"{0}\".", customer.getPassword());
        } else {
            try {
                return gif.get();
            } catch (Exception e) {
                return MessageFormat.format("I cannot help with that request, {0}.", customer.getFirstName());
            }
        }
    }

    boolean verifySecurityQuestionAnswer(SecurityQuestionAnswer questionAnswer, String givenAnswer) {
        return questionAnswer.getAnswer().equals(givenAnswer);
    }

    private Customer resetCustomerPassword(Customer customer) {
        String newPassword = RandomStringUtils.random(10);
        customer.setPassword(newPassword);
        customerRepository.save(customer);
        return customer;
    }

}
