package com.solstice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.constraints.AssertFalse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Justin Baumgartner on 5/23/17.
 */
public class JUnit4Test {

    // JUnit4 Rule
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    // JUnit4 Test
    @Test
    public void throwsIllegalArgumentException_JUnit4Rule() {
        exception.expect(IllegalArgumentException.class);
        throw new IllegalArgumentException("");
    }

    // JUnit5 Test
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("JUnit 5 Exception Test Using assertThrows")
    @org.junit.jupiter.api.Tag("JUnit 5 Test")
    public void throwsIllegalArgumentException_JUnit5Rule() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("The supplied arguments are invalid");
        });

        assertTrue(exception.getMessage() == "The supplied arguments are invalid");
    }
}
