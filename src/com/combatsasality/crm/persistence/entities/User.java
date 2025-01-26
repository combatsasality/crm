package com.combatsasality.crm.persistence.entities;

import com.combatsasality.crm.HelpHandler;
import com.combatsasality.crm.persistence.Entity;

import java.util.Objects;


public class User extends Entity {
    private final String username;
    private final String password;
    private final Role role;


    public User(String username, String password, Role role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password) {
        this(username, password, Role.DEFAULT);
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return this.role;
    }

    public enum Role{
        DEFAULT,
        ADMIN,
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User user && user.getUsername().equals(this.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role);
    }

    public boolean equalsPassword(String password) {
        return HelpHandler.sha256(password).equals(this.password);
    }

}
