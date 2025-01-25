package com.combatsasality.crm.persistence;

import java.util.Date;
import java.util.UUID;

public abstract class Entity {
    protected final UUID id;
//    protected final Date createdAt;

    public Entity() {
        this.id = UUID.randomUUID();
//        this.createdAt = new Date();
    }

    public UUID getId() {
        return this.id;
    }

//    public Date getCreatedAt() {
//        return this.createdAt;
//    }

}
