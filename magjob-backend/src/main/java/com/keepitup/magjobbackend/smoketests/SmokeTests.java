package com.keepitup.magjobbackend.smoketests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SmokeTests {
    private final RestTemplate restTemplate;

    // TODO complete endpoints list
    private final String[] endpointsToCheck = {
            "http://localhost:8080/endpoint1",
            "http://localhost:8080/endpoint2"
    };

    @Autowired
    public SmokeTests(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck() {
        // TODO execute methods for endpoints
        for (String endpoint : endpointsToCheck) {
            ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Endpoint: " + endpoint + " is not working properly");
            }
        }
        return ResponseEntity.ok("All endpoint work properly");
    }
}
