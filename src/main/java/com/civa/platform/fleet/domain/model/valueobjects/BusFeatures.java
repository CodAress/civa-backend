package com.civa.platform.fleet.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record BusFeatures(String features) {
    public BusFeatures {
        if (features == null || features.isBlank()) {
            throw new IllegalArgumentException("Bus features cannot be null or empty");
        }
    }
}
