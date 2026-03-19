package com.goosage.roboops.robot.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.robot.api.dto.RobotCommandRequest;
import com.goosage.roboops.robot.api.dto.RobotCommandResponse;
import com.goosage.roboops.robot.application.RobotService;
import com.goosage.roboops.robot.domain.Robot;

import jakarta.validation.Valid;

@RestController
public class RobotQueryController {

    private final RobotService robotService;

    public RobotQueryController(RobotService robotService) {
        this.robotService = robotService;
    }

    @GetMapping("/api/robots")
    public List<Robot> getRobots() {
        return robotService.getRobots();
    }

    @GetMapping("/api/robots/{code}")
    public Robot getRobot(@PathVariable String code) {
        return robotService.getRobotByCode(code);
    }

    @PutMapping("/api/robots/{code}/command")
    public RobotCommandResponse command(
            @PathVariable String code,
            @Valid @RequestBody RobotCommandRequest request
    ) {
        Robot updated = robotService.updateStatus(code, request.status());

        return new RobotCommandResponse(
                updated.code(),
                updated.status(),
                "command applied"
        );
    }
}