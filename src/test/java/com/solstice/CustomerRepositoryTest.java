package com.solstice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Justin Baumgartner on 5/21/17.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {

    @Test
    void findByUsernameShouldReturnCustomer(@Autowired TestEntityManager entityManager, @Autowired CustomerRepository repository) {
        Customer customer = new Customer("1", "testuser", "pass1234", "Test");
        customer.addSecurityQuestionAnswer(new SecurityQuestionAnswer("Who is the best artist ever?", "Rush"));
        entityManager.persist(customer);

        Customer customerFromRepo = repository.findByUsername(customer.getUsername());
        assertEquals(customer.getId(), customerFromRepo.getId());
        assertEquals(customer.getUsername(), customerFromRepo.getUsername());

        assertEquals(customer.getSecurityQuestionAnswers().size(), customerFromRepo.getSecurityQuestionAnswers().size());
        assertEquals(customer.getSecurityQuestionAnswers().get(0).getAnswer(), customerFromRepo.getSecurityQuestionAnswers().get(0).getAnswer());
    }

}
