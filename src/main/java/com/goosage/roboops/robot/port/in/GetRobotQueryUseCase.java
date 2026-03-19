package com.goosage.roboops.robot.port.in;

import java.util.List;

import com.goosage.roboops.robot.domain.Robot;

public interface GetRobotQueryUseCase {

    List<Robot> getAll();

    Robot getByCode(String code);
}