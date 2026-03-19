package com.goosage.roboops.telemetry.application;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.goosage.roboops.event.domain.EventType;
import com.goosage.roboops.event.port.out.SaveRobotEventPort;
import com.goosage.roboops.robot.application.RobotService;
import com.goosage.roboops.robot.domain.HealthState;
import com.goosage.roboops.robot.domain.RobotState;
import com.goosage.roboops.robot.domain.RobotStatus;
import com.goosage.roboops.telemetry.dto.TelemetryRequest;
import com.goosage.roboops.telemetry.dto.TelemetryResponse;
import com.goosage.roboops.telemetry.port.in.ReceiveTelemetryUseCase;

@Service
public class TelemetryService implements ReceiveTelemetryUseCase {

    private final RobotService robotService;
    private final SaveRobotEventPort saveRobotEventPort;
    private final RobotHealthPolicy robotHealthPolicy;
    private final RobotOfflineChecker robotOfflineChecker;

    public TelemetryService(RobotService robotService,
                            SaveRobotEventPort saveRobotEventPort,
                            RobotHealthPolicy robotHealthPolicy,
                            RobotOfflineChecker robotOfflineChecker) {
        this.robotService = robotService;
        this.saveRobotEventPort = saveRobotEventPort;
        this.robotHealthPolicy = robotHealthPolicy;
        this.robotOfflineChecker = robotOfflineChecker;
    }

    @Override
    public TelemetryResponse receive(TelemetryRequest request) {
        RobotState robot = robotService.getStateByCode(request.getRobotCode());

        if (robot == null) {
            throw new IllegalArgumentException("Robot not found: " + request.getRobotCode());
        }

        LocalDateTime observedAt = request.getObservedAt() != null
                ? request.getObservedAt()
                : LocalDateTime.now();

        robot.updateTelemetry(
                request.getBatteryLevel(),
                request.getLocation(),
                request.getCurrentTask(),
                observedAt,
                request.getErrorCode()
        );

        RobotHealthDecision decision = robotHealthPolicy.decide(robot, request);

        robot.applyHealth(
                decision.getStatus(),
                decision.getHealthState(),
                decision.getRecommendedAction()
        );

        robotService.saveState(robot);

        saveRobotEventPort.createAndSave(
                EventType.TELEMETRY_RECEIVED,
                robot.getCode(),
                "Telemetry received"
        );

        if (decision.getHealthState() == HealthState.LOW_BATTERY) {
            saveRobotEventPort.createAndSave(
                    EventType.LOW_BATTERY_DETECTED,
                    robot.getCode(),
                    decision.getMessage()
            );
        }

        if (decision.getHealthState() == HealthState.ROBOT_ERROR) {
            saveRobotEventPort.createAndSave(
                    EventType.ROBOT_ERROR_DETECTED,
                    robot.getCode(),
                    decision.getMessage()
            );
        }

        robotOfflineChecker.check();

        String responseMessage = decision.getMessage();

        if (robot.getStatus() == RobotStatus.OFFLINE) {
            responseMessage = "Robot offline detected";
        } else if (robot.getStatus() == RobotStatus.ERROR) {
            responseMessage = "Robot error detected";
        } else if (robot.getHealthState() == HealthState.LOW_BATTERY) {
            responseMessage = "Low battery detected";
        }

        return new TelemetryResponse(
                robot.getCode(),
                robot.getStatus(),
                robot.getHealthState(),
                robot.getRecommendedAction(),
                responseMessage
        );
    }
}