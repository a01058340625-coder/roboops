package com.goosage.roboops.telemetry.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.telemetry.application.TelemetryService;
import com.goosage.roboops.telemetry.dto.TelemetryRequest;
import com.goosage.roboops.telemetry.dto.TelemetryResponse;

import jakarta.validation.Valid;

@RestController
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @PostMapping("/api/telemetry")
    public TelemetryResponse receive(@Valid @RequestBody TelemetryRequest request) {
        return telemetryService.receive(request);
    }
}