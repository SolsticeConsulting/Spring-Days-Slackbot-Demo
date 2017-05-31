package com.solstice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Justin Baumgartner on 5/21/17.
 */
class RandomStringUtilsTest {

    //A parameterized test that validates StringUtil class's ability to generate
    //a random string of a specified length
    @ParameterizedTest
    @ValueSource(ints = { 2, 4, 8, 16, 32 })
    void randomShouldBeTheCorrectLength(int expectedLength) {
        String randomString = RandomStringUtils.random(expectedLength);
        assertEquals(expectedLength, randomString.length());
    }

}
