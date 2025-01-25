package com.combatsasality.crm;

import com.combatsasality.crm.persistence.entities.User;
import com.combatsasality.crm.persistence.exceptions.BadValidationException;
import com.combatsasality.crm.persistence.repositories.UserRepository;

public class Main {
    public static void main(String[] args) {
        try {
            User user1 = new User("combatsasality1", "3545231d");
            User user2 = new User("combatsasality2", "3545231d");
            UserRepository repository = new UserRepository();

            repository.add(user1);
            repository.add(user2);

            repository.save();

        } catch (BadValidationException e) {
            System.out.println(e);
        }



    }
}