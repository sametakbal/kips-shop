package com.kips.product.api.domain.enumaretion;

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

    public static EntityType fromType(String type) {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.getType().equals(type)) {
                return entityType;
            }
        }
        return null;
    }
}
