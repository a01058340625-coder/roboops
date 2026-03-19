package com.goosage.roboops.robot.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RobotCommandRequest(
        @NotBlank String status
) {
}