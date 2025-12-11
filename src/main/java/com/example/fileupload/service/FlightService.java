package com.example.fileupload.service;

import com.example.fileupload.model.Flight;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FlightService {

    public void saveFlight(Flight flight) {
        // In real app → save to DB
        System.out.println("Saving: " + flight.getFlightNumber());
    }

    public Map<String, String> validateFlight(Flight flight) {

        Map<String, String> errors = new HashMap<>();

        if (flight.getFlightNumber() == null || flight.getFlightNumber().isBlank())
            errors.put("flightNumber", "Flight number is required");

        if (flight.getSource() == null || flight.getSource().isBlank())
            errors.put("source", "Source cannot be empty");

        if (flight.getDestination() == null || flight.getDestination().isBlank())
            errors.put("destination", "Destination cannot be empty");

        // Example rule — you can customize
        if ("ABC".equalsIgnoreCase(flight.getDestination()))
            errors.put("destination", "invalid destination code");

        if (flight.getDepartureTime() == null || flight.getDepartureTime().isBlank())
            errors.put("departureTime", "Departure time missing");

        return errors;
    }
}
