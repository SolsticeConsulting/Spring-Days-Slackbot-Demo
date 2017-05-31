package com.solstice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Justin Baumgartner on 5/21/17.
 */
//Registration of the CustomerResolver class on the Customer Test container
@ExtendWith(CustomerResolver.class)
class CustomerTest {

    // Demonstrates lambda expression and the lazy loading of message within an assertion.
    @Test
    @DisplayName("Password Persistance Test")
    @Tag("CustomerTests")
    void newPasswordShouldPersist(Customer customer) {
        String expectedNewPassword = "newPassword";
        customer.setPassword(expectedNewPassword);

        assertEquals(expectedNewPassword, customer.getPassword(),
                () -> "The expected is: " + expectedNewPassword + " while the actual is " + customer.getPassword());
    }

    // Demonstrates using the new `assertAll` assertion.
    @Test
    @DisplayName("Security Question Persistance Test")
    @Tag("CustomerTests")
    void addSecurityQuestionShouldPersist(Customer customer) {
        SecurityQuestionAnswer questionAnswer = new SecurityQuestionAnswer("Who is the best artist ever?", "Rush");
        customer.addSecurityQuestionAnswer(questionAnswer);

        assertEquals(1, customer.getSecurityQuestionAnswers().size());
        assertAll("Assert question answer properties", () -> {
            SecurityQuestionAnswer persistedQuestionAnswer = customer.getSecurityQuestionAnswers().get(0);
            assertEquals("Who is the best artist ever?", persistedQuestionAnswer.getQuestion());
            assertEquals("Rush", persistedQuestionAnswer.getAnswer());
        });
    }
}
