package com.goosage.roboops.robot.port.out;

import com.goosage.roboops.robot.domain.RobotState;

public interface SaveRobotStatePort {

    RobotState save(RobotState robotState);
}