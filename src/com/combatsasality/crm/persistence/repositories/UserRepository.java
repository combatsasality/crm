package com.combatsasality.crm.persistence.repositories;

import com.combatsasality.crm.persistence.Repository;
import com.combatsasality.crm.persistence.entities.User;
import com.google.gson.reflect.TypeToken;

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

    @Override
    public void add(User ent) {
        if (entities.stream().anyMatch(u -> u.getUsername().equals(ent.getUsername()))) {
            return;
        }
        super.add(ent);
    }
}
