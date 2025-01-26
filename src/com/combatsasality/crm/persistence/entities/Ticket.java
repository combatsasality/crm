package com.combatsasality.crm.persistence.entities;

import com.combatsasality.crm.HelpHandler;
import com.combatsasality.crm.persistence.Entity;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public class Ticket extends Entity {
    private long validUntil;
    private final UUID userId;

    public Ticket(long validUntil, UUID userId) {
        this.validUntil = validUntil;
        this.userId = userId;
    }

    public boolean isAvailable() {
        return System.currentTimeMillis() < this.validUntil;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getValidUntil() {
        return Instant.ofEpochMilli(this.validUntil).atZone(ZoneId.systemDefault()).toLocalDateTime().format(HelpHandler.dateFormatter);
    }

    public void addTime(long time) {
        this.validUntil = this.validUntil + time;
    }

}
