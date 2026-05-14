package com.nttoan.handmadeshop.domain.catalog.category.entity;

import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

public class Category extends BaseEntity {
    private String name;
    private String description;
    private boolean active;

    public Category(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        this.name = name;
        this.description = description;
        this.active = true;
    }

    public static Category restore(
            String id,
            String name,
            String description,
            boolean active) {
        Category c = new Category(name, description);
        c.setId(id);

        if (!active) {
            c.deactivate();
        }

        return c;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }
}
