package com.solstice;

import java.security.SecureRandom;

/**
 * Created by Justin Baumgartner on 5/18/17.
 */
public class RandomStringUtils {

    static final String validCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom random = new SecureRandom();

    static String random(int count) {
        StringBuilder builder = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            char c = validCharacters.charAt(random.nextInt(validCharacters.length()));
            builder.append(c);
        }
        return builder.toString();
    }
}
