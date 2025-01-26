package com.combatsasality.crm.persistence.repositories;

import com.combatsasality.crm.persistence.Repository;
import com.combatsasality.crm.persistence.entities.User;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

public class UserRepository extends Repository<User> {


    public UserRepository() {
        super(
                "users.json", TypeToken.getParameterized(Set.class, User.class).getType()
        );
    }

    public Optional<User> findByUsername(String username) {
        return entities.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }



    @Nullable
    public User authenticate(String username, String password) {
        Optional<User> user = findByUsername(username);
        if (!user.isPresent()) {
            return null;
        }
        User userObject = user.get();
        if (userObject.equalsPassword(password)) {
            return userObject;
        }
        return null;
    }
}
