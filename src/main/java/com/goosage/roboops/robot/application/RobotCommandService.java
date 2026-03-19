package com.goosage.roboops.robot.application;

import org.springframework.stereotype.Service;

import com.goosage.roboops.event.domain.EventType;
import com.goosage.roboops.event.port.out.SaveRobotEventPort;
import com.goosage.roboops.robot.api.dto.RobotCommandResponse;
import com.goosage.roboops.robot.domain.HealthState;
import com.goosage.roboops.robot.domain.RecommendedAction;
import com.goosage.roboops.robot.domain.RobotState;
import com.goosage.roboops.robot.domain.RobotStatus;
import com.goosage.roboops.robot.port.in.SendRobotCommandUseCase;

@Service
public class RobotCommandService implements SendRobotCommandUseCase {

    private final RobotService robotService;
    private final SaveRobotEventPort saveRobotEventPort;

    public RobotCommandService(RobotService robotService,
                               SaveRobotEventPort saveRobotEventPort) {
        this.robotService = robotService;
        this.saveRobotEventPort = saveRobotEventPort;
    }

    @Override
    public RobotCommandResponse sendCommand(String robotCode, String command) {
        RobotState robot = robotService.getStateByCode(robotCode);

        if (robot == null) {
            throw new IllegalArgumentException("Robot not found: " + robotCode);
        }

        String normalized = command == null ? "" : command.trim().toUpperCase();

        if (normalized.isBlank()) {
            throw new IllegalArgumentException("Command must not be blank");
        }

        applyCommand(robot, normalized);
        robotService.saveState(robot);

        saveRobotEventPort.createAndSave(
                EventType.COMMAND_SENT,
                robotCode,
                "Command sent: " + normalized
        );

        return new RobotCommandResponse(
                robotCode,
                normalized,
                "ACCEPTED",
                "Command applied successfully"
        );
    }

    private void applyCommand(RobotState robot, String command) {
        switch (command) {
            case "RETURN_TO_CHARGER" -> robot.applyHealth(
                    RobotStatus.CHARGING,
                    HealthState.NORMAL,
                    RecommendedAction.CONTINUE_WORK
            );

            case "STOP" -> robot.applyHealth(
                    RobotStatus.IDLE,
                    HealthState.NORMAL,
                    RecommendedAction.CONTINUE_WORK
            );

            case "RESTART" -> robot.applyHealth(
                    RobotStatus.IDLE,
                    HealthState.NORMAL,
                    RecommendedAction.CONTINUE_WORK
            );

            case "RESUME" -> robot.applyHealth(
                    RobotStatus.WORKING,
                    HealthState.NORMAL,
                    RecommendedAction.CONTINUE_WORK
            );

            default -> throw new IllegalArgumentException("Unsupported command: " + command);
        }
    }
}