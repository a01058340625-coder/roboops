package com.goosage.roboops.robot.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goosage.roboops.robot.domain.Robot;
import com.goosage.roboops.robot.domain.RobotState;
import com.goosage.roboops.robot.port.in.GetRobotQueryUseCase;
import com.goosage.roboops.robot.port.out.LoadRobotStatePort;
import com.goosage.roboops.robot.port.out.SaveRobotStatePort;

@Service
public class RobotService implements GetRobotQueryUseCase {

    private final LoadRobotStatePort loadRobotStatePort;
    private final SaveRobotStatePort saveRobotStatePort;

    public RobotService(LoadRobotStatePort loadRobotStatePort,
                        SaveRobotStatePort saveRobotStatePort) {
        this.loadRobotStatePort = loadRobotStatePort;
        this.saveRobotStatePort = saveRobotStatePort;
    }

    @Override
    public List<Robot> getAll() {
        return loadRobotStatePort.loadAll().stream()
                .map(RobotState::toRobot)
                .toList();
    }

    @Override
    public Robot getByCode(String code) {
        RobotState state = loadRobotStatePort.loadByCode(code);
        return state == null ? null : state.toRobot();
    }

    public RobotState getStateByCode(String code) {
        return loadRobotStatePort.loadByCode(code);
    }

    public RobotState saveState(RobotState robotState) {
        return saveRobotStatePort.save(robotState);
    }

    public List<RobotState> getAllStates() {
        return loadRobotStatePort.loadAll();
    }
}