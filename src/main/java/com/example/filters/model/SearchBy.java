package com.example.filters.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SearchBy {
    AMOUNT("Amount"),
    TITLE("Title"),
    DATE("Date");

    private final String value;

    SearchBy(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SearchBy forValue(String value) {
        for (SearchBy searchBy : values()) {
            if (searchBy.value.equalsIgnoreCase(value)) {
                return searchBy;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
