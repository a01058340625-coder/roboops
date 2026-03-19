package com.goosage.roboops.telemetry.application;

import org.springframework.stereotype.Service;

import com.goosage.roboops.event.application.EventService;
import com.goosage.roboops.robot.application.RobotService;
import com.goosage.roboops.telemetry.dto.TelemetryRequest;
import com.goosage.roboops.telemetry.dto.TelemetryResponse;

@Service
public class TelemetryService {

    private final EventService eventService;
    private final RobotService robotService;

    public TelemetryService(EventService eventService, RobotService robotService) {
        this.eventService = eventService;
        this.robotService = robotService;
    }

    public TelemetryResponse receive(TelemetryRequest request) {
        validateBatteryLevel(request.batteryLevel());

        robotService.syncStatusFromTelemetry(request.robotCode(), request.status());

        String alertLevel = request.batteryLevel() <= 20 ? "WARNING" : "NORMAL";
        String message = request.batteryLevel() <= 20
                ? "low battery"
                : "telemetry received";

        if (request.batteryLevel() <= 20) {
            eventService.publish(
                    "LOW_BATTERY",
                    request.robotCode(),
                    "Battery level is " + request.batteryLevel()
            );
        }

        return new TelemetryResponse(
                request.robotCode(),
                request.status(),
                request.batteryLevel(),
                alertLevel,
                message
        );
    }

    private void validateBatteryLevel(Integer batteryLevel) {
        if (batteryLevel == null) {
            throw new IllegalArgumentException("batteryLevel is required");
        }

        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("batteryLevel must be between 0 and 100");
        }
    }
}