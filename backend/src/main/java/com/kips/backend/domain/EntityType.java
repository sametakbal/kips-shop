package com.kips.backend.domain;

public enum EntityType {
    PRODUCT("product");

    private final String type;

    EntityType(String type) {
        this.type = type;
    }
}
