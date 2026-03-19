package com.goosage.roboops.robot.port.out;

import java.util.List;

import com.goosage.roboops.robot.domain.RobotState;

public interface LoadRobotStatePort {

    RobotState loadByCode(String code);

    List<RobotState> loadAll();
}