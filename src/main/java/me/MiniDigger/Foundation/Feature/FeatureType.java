package me.MiniDigger.Foundation.Feature;

import lombok.Getter;

public enum FeatureType {

    DEBUG("DEBUG");

    @Getter
    private String name;

    FeatureType(String name) {
        this.name = name;
    }
}
