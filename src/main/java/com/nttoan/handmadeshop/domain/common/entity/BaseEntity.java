package com.nttoan.handmadeshop.domain.common.entity;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseEntity {
    protected String id;
    protected Instant createdAt;
    protected Instant updatedAt;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void markUpdated() {
        this.updatedAt = Instant.now();
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
