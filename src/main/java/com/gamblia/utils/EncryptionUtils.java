package com.gamblia.utils;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class EncryptionUtils {

    private static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR = new StrongPasswordEncryptor();

    public static String encryptPassword(String password) {
        return PASSWORD_ENCRYPTOR.encryptPassword(password);
    }

    public static boolean checkPassword(String plainPassword, String enncryptedPasword) {
        return PASSWORD_ENCRYPTOR.checkPassword(plainPassword, enncryptedPasword);
    }
}
