package com.example.fileupload.controller;

import com.example.fileupload.model.Flight;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CsvFlightParser {

    public static List<Flight> parse(MultipartFile file) throws Exception {

        List<Flight> flights = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

        String line;
        boolean skipHeader = true;

        while ((line = br.readLine()) != null) {

            if (skipHeader) {
                skipHeader = false;
                continue;
            }

            String[] data = line.split(",");

            Flight f = new Flight();
            f.setFlightNumber(data.length > 0 ? data[0].trim() : "");
            f.setSource(data.length > 1 ? data[1].trim() : "");
            f.setDestination(data.length > 2 ? data[2].trim() : "");
            f.setDepartureTime(data.length > 3 ? data[3].trim() : "");

            flights.add(f);
        }

        return flights;
    }
}
