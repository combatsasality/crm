package com.combatsasality.crm.persistence.repositories;

import com.combatsasality.crm.persistence.Repository;
import com.combatsasality.crm.persistence.entities.Ticket;
import com.google.gson.reflect.TypeToken;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class TicketRepository extends Repository<Ticket> {
    public TicketRepository() {
        super("tickets.json", TypeToken.getParameterized(Set.class, Ticket.class).getType());
    }

    public Optional<Ticket> findAvailableTicketByUserId(UUID userId) {
        return entities.stream().filter(u -> u.isAvailable() && u.getUserId().equals(userId)).findFirst();
    }



}
