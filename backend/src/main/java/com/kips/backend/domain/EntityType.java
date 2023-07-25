package com.kips.backend.domain;

public enum EntityType {
    PRODUCT("product"),
    REVIEW("review");

    private final String type;

    EntityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
