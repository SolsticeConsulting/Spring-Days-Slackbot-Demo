package com.solstice;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Justin Baumgartner on 5/21/17.
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(CustomerResolver.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    @InjectMocks
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    void setupEach(Customer mockCustomer) {
        mockCustomer.addSecurityQuestionAnswer(new SecurityQuestionAnswer("Who is the best artist ever?", "Rush"));
        when(customerRepository.findOne("1")).thenReturn(mockCustomer);
    }

    // Demonstrates a dynamic test
    @TestFactory
    Stream<DynamicTest> shouldProcessResetPasswordMessage() {
        ArrayList<String> resetPasswordMessages = new ArrayList<>(
                Arrays.asList("Reset my password.",
                        "I want to reset my password.",
                        "Can you reset my password?"));
        String expectedResponsePrefix = "Hi Justin, I can help with that! All I need to know is one security question. \n\nWho is the best artist ever?";

        return resetPasswordMessages.stream().map(message -> DynamicTest.dynamicTest("Process message: " + message, () -> {
            String response = customerService.handleCustomerMessage("1", message);
            assertTrue(response.startsWith(expectedResponsePrefix));
        }));
    }

    @Test
    void verifySecurityQuestionAnswerShouldEquate() {
        SecurityQuestionAnswer questionAnswer = customerRepository.findOne("1").getSecurityQuestionAnswers().get(0);
        assertTrue(customerService.verifySecurityQuestionAnswer(questionAnswer, "Rush"));
        assertFalse(customerService.verifySecurityQuestionAnswer(questionAnswer, "AC/DC"));
    }

}
