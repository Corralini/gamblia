package com.gamblia.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static boolean validateEmail (String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
