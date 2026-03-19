package com.goosage.roboops.robot.api.dto;

public record RobotCommandResponse(
        String code,
        String status,
        String message
) {
}