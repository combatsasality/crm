package com.combatsasality.crm.persistence.entities;

import com.combatsasality.crm.HelpHandler;
import com.combatsasality.crm.persistence.Entity;
import com.combatsasality.crm.persistence.exceptions.BadValidationException;

import static com.combatsasality.crm.HelpHandler.passwordPattern;


public class User extends Entity {
    private final String username;
    private final String password;
    private final Role role;


    public User(String username, String password, Role role) throws BadValidationException {
        super();
        this.username = username;
        this.password = validatePassword(password);
        this.role = role;
    }

    public User(String username, String password) throws BadValidationException {
        this(username, password, Role.DEFAULT);
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return this.role;
    }

    private String validatePassword(String password) throws BadValidationException {
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

    public enum Role{
        DEFAULT,
        ADMIN,
    }


}
