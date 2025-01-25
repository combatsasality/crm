package com.combatsasality.crm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HelpHandler {
    public static String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
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
}
