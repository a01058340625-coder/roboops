package com.goosage.roboops.telemetry.dto;

public record TelemetryResponse(
        String robotCode,
        String status,
        Integer batteryLevel,
        String alertLevel,
        String message
) {
}