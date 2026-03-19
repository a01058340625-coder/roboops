package com.goosage.roboops.robot.port.in;

import com.goosage.roboops.robot.api.dto.RobotCommandResponse;

public interface SendRobotCommandUseCase {

    RobotCommandResponse sendCommand(String robotCode, String command);
}