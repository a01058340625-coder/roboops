package com.goosage.roboops.robot.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.robot.domain.Robot;
import com.goosage.roboops.robot.port.in.GetRobotQueryUseCase;

@RestController
@RequestMapping("/api/robots")
public class RobotQueryController {

    private final GetRobotQueryUseCase getRobotQueryUseCase;

    public RobotQueryController(GetRobotQueryUseCase getRobotQueryUseCase) {
        this.getRobotQueryUseCase = getRobotQueryUseCase;
    }

    @GetMapping
    public List<Robot> getAll() {
        return getRobotQueryUseCase.getAll();
    }

    @GetMapping("/{code}")
    public Robot getOne(@PathVariable String code) {
        return getRobotQueryUseCase.getByCode(code);
    }
}