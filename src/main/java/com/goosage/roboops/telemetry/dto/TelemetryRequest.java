package com.goosage.roboops.telemetry.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelemetryRequest(
        @NotBlank String robotCode,
        @NotBlank String status,
        @NotNull Integer batteryLevel
) {
}