package com.goosage.roboops.robot.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.robot.api.dto.RobotCommandRequest;
import com.goosage.roboops.robot.api.dto.RobotCommandResponse;
import com.goosage.roboops.robot.port.in.SendRobotCommandUseCase;

@RestController
@RequestMapping("/api/robots")
public class RobotCommandController {

    private final SendRobotCommandUseCase sendRobotCommandUseCase;

    public RobotCommandController(SendRobotCommandUseCase sendRobotCommandUseCase) {
        this.sendRobotCommandUseCase = sendRobotCommandUseCase;
    }

    @PostMapping("/{robotCode}/commands")
    public RobotCommandResponse sendCommand(@PathVariable String robotCode,
                                            @RequestBody RobotCommandRequest request) {
        return sendRobotCommandUseCase.sendCommand(robotCode, request.getCommand());
    }
}