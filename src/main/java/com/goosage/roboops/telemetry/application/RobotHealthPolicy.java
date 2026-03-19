package com.goosage.roboops.telemetry.application;

import org.springframework.stereotype.Component;

import com.goosage.roboops.robot.domain.HealthState;
import com.goosage.roboops.robot.domain.RecommendedAction;
import com.goosage.roboops.robot.domain.RobotState;
import com.goosage.roboops.robot.domain.RobotStatus;
import com.goosage.roboops.telemetry.dto.TelemetryRequest;

@Component
public class RobotHealthPolicy {

    public RobotHealthDecision decide(RobotState robot, TelemetryRequest request) {

        if (request.getErrorCode() != null && !request.getErrorCode().isBlank()) {
            return new RobotHealthDecision(
                    RobotStatus.ERROR,
                    HealthState.ROBOT_ERROR,
                    RecommendedAction.DISPATCH_TECHNICIAN,
                    "Robot error detected: " + request.getErrorCode()
            );
        }

        if (request.getBatteryLevel() < 20) {
            return new RobotHealthDecision(
                    RobotStatus.WARNING,
                    HealthState.LOW_BATTERY,
                    RecommendedAction.RETURN_TO_CHARGER,
                    "Low battery detected"
            );
        }

        if ("CHARGING".equalsIgnoreCase(request.getCurrentTask())) {
            return new RobotHealthDecision(
                    RobotStatus.CHARGING,
                    HealthState.NORMAL,
                    RecommendedAction.CONTINUE_WORK,
                    "Charging"
            );
        }

        if (request.getCurrentTask() != null
                && !request.getCurrentTask().isBlank()
                && !"NONE".equalsIgnoreCase(request.getCurrentTask())) {
            return new RobotHealthDecision(
                    RobotStatus.WORKING,
                    HealthState.NORMAL,
                    RecommendedAction.CONTINUE_WORK,
                    "Working normally"
            );
        }

        return new RobotHealthDecision(
                RobotStatus.IDLE,
                HealthState.NORMAL,
                RecommendedAction.CONTINUE_WORK,
                "Idle and healthy"
        );
    }
}