package com.combatsasality.crm.persistence;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
    protected final UUID id;
    protected final long createdAt;

    protected Entity() {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return this.id;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Entity ent && ent.getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }
}
