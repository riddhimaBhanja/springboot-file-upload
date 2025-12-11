package com.example.fileupload.controller;

import com.example.fileupload.model.Flight;
import com.example.fileupload.model.FlightBatchResponse;
import com.example.fileupload.model.FlightError;
import com.example.fileupload.service.FlightService;
import com.example.fileupload.util.CsvFlightParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/flights")
public class FlightBatchController {

    @Autowired
    private FlightService flightService;

    // -------------------------------------------
    // 1) JSON batch upload
    // -------------------------------------------
    @PostMapping("/batch")
    public ResponseEntity<FlightBatchResponse> uploadBatch(@RequestBody List<Flight> flights) {

        List<Flight> validFlights = new ArrayList<>();
        List<FlightError> errors = new ArrayList<>();

        int index = 0;
        for (Flight flight : flights) {

            Map<String, String> validationErrors = flightService.validateFlight(flight);

            if (!validationErrors.isEmpty()) {
                FlightError errorObj = new FlightError(index);
                validationErrors.forEach(errorObj::addError);
                errors.add(errorObj);
            } else {
                validFlights.add(flight);
                flightService.saveFlight(flight);
            }
            index++;
        }

        FlightBatchResponse response = new FlightBatchResponse(validFlights, errors);
        return ResponseEntity.ok(response);
    }

    // -------------------------------------------
    // 2) CSV file batch upload
    // -------------------------------------------
    @PostMapping("/batch/upload")
    public ResponseEntity<?> uploadBatchFile(@RequestParam("file") MultipartFile file) {

        try {
            List<Flight> flights = CsvFlightParser.parse(file);

            List<Flight> validFlights = new ArrayList<>();
            List<FlightError> errors = new ArrayList<>();

            int index = 0;
            for (Flight flight : flights) {

                Map<String, String> validationErrors = flightService.validateFlight(flight);

                if (!validationErrors.isEmpty()) {
                    FlightError errorObj = new FlightError(index);
                    validationErrors.forEach(errorObj::addError);
                    errors.add(errorObj);
                } else {
                    validFlights.add(flight);
                    flightService.saveFlight(flight);
                }
                index++;
            }

            FlightBatchResponse response = new FlightBatchResponse(validFlights, errors);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }
}
