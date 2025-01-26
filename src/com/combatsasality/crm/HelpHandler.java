package com.combatsasality.crm;

import com.combatsasality.crm.persistence.exceptions.BadValidationException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class HelpHandler {
    public static final String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");


    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            // Not possible
            return "";
        }
    }

    public static String validatePassword(String password) throws BadValidationException {
        int passwordLength = password.length();
        if (passwordLength < 8) {
            throw new BadValidationException("Пароль занадто маленький");
        } else if (passwordLength > 32) {
            throw new BadValidationException("Пароль занадто великий");
        }
        if (!password.matches(passwordPattern)) {
            throw new BadValidationException("Пароль має мати одну цифру і один символ");
        }

        return HelpHandler.sha256(password);
    }

}
