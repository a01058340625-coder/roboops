package com.goosage.roboops.telemetry.application;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.goosage.roboops.event.domain.EventType;
import com.goosage.roboops.event.port.out.SaveRobotEventPort;
import com.goosage.roboops.robot.application.RobotService;
import com.goosage.roboops.robot.domain.HealthState;
import com.goosage.roboops.robot.domain.RecommendedAction;
import com.goosage.roboops.robot.domain.RobotState;
import com.goosage.roboops.robot.domain.RobotStatus;

@Component
public class RobotOfflineChecker {

    private final RobotService robotService;
    private final SaveRobotEventPort saveRobotEventPort;

    public RobotOfflineChecker(RobotService robotService,
                               SaveRobotEventPort saveRobotEventPort) {
        this.robotService = robotService;
        this.saveRobotEventPort = saveRobotEventPort;
    }

    public void check() {
        List<RobotState> robots = robotService.getAllStates();
        LocalDateTime now = LocalDateTime.now();

        for (RobotState robot : robots) {
            if (robot.getLastTelemetryAt() == null) {
                continue;
            }

            long minutes = Duration.between(robot.getLastTelemetryAt(), now).toMinutes();

            if (minutes >= 5 && robot.getStatus() != RobotStatus.OFFLINE) {
                robot.applyHealth(
                        RobotStatus.OFFLINE,
                        HealthState.TELEMETRY_LOST,
                        RecommendedAction.CHECK_ROBOT
                );

                robotService.saveState(robot);

                saveRobotEventPort.createAndSave(
                        EventType.ROBOT_OFFLINE_DETECTED,
                        robot.getCode(),
                        "Robot offline detected"
                );
            }
        }
    }
}