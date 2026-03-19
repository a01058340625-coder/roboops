package com.goosage.roboops.telemetry.port.in;

import com.goosage.roboops.telemetry.dto.TelemetryRequest;
import com.goosage.roboops.telemetry.dto.TelemetryResponse;

public interface ReceiveTelemetryUseCase {

    TelemetryResponse receive(TelemetryRequest request);
}