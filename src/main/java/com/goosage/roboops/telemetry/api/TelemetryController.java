package com.goosage.roboops.telemetry.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.telemetry.dto.TelemetryRequest;
import com.goosage.roboops.telemetry.dto.TelemetryResponse;
import com.goosage.roboops.telemetry.port.in.ReceiveTelemetryUseCase;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    private final ReceiveTelemetryUseCase receiveTelemetryUseCase;

    public TelemetryController(ReceiveTelemetryUseCase receiveTelemetryUseCase) {
        this.receiveTelemetryUseCase = receiveTelemetryUseCase;
    }

    @PostMapping
    public TelemetryResponse receive(@RequestBody TelemetryRequest request) {
        return receiveTelemetryUseCase.receive(request);
    }
}