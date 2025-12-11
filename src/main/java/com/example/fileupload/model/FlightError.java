package com.example.fileupload.model;

import java.util.HashMap;
import java.util.Map;

public class FlightError {

    private int index;
    private Map<String, String> errors = new HashMap<>();

    public FlightError(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String field, String message) {
        errors.put(field, message);
    }
}
