package com.example.filters.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ComparisonType {
    EQUALS("Equals"),
    CONTAINS("Contains"),
    STARTS_WITH("Starts With"),
    ENDS_WITH("Ends With"),
    GREATER_THAN("Greater Than"),
    LESS_THAN("Less Than"),
    BEFORE("Before"),
    AFTER("After");

    private final String value;

    ComparisonType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ComparisonType forValue(String value) {
        for (ComparisonType comparisonType : values()) {
            if (comparisonType.value.equalsIgnoreCase(value)) {
                return comparisonType;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
