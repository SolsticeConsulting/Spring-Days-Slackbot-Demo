package com.solstice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Justin Baumgartner on 5/18/17.
 */
@Component
public class CustomerDataLoader implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Load customer data here
        // Customer customer1 = new Customer("XXXYYYZZZ", "name_goes_here", "password_goes_here", "name_goes_here");
        // customer1.addSecurityQuestionAnswer(new SecurityQuestionAnswer("security_question_goes_here", "security_answer_goes_here"));
        // customerRepository.save(customer1);
    }

}
