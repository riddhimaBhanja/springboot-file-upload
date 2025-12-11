package com.example.fileupload.model;

import java.util.List;

public class FlightBatchResponse {

    private List<Flight> successful;
    private List<FlightError> errors;

    public FlightBatchResponse(List<Flight> successful, List<FlightError> errors) {
        this.successful = successful;
        this.errors = errors;
    }

    public List<Flight> getSuccessful() { return successful; }
    public List<FlightError> getErrors() { return errors; }
}
