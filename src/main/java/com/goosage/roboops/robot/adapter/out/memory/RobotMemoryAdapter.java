package com.goosage.roboops.robot.adapter.out.memory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.goosage.roboops.robot.domain.RobotState;
import com.goosage.roboops.robot.port.out.LoadRobotStatePort;
import com.goosage.roboops.robot.port.out.SaveRobotStatePort;

@Component
public class RobotMemoryAdapter implements LoadRobotStatePort, SaveRobotStatePort {

    private final Map<String, RobotState> robotStore = new LinkedHashMap<>();

    public RobotMemoryAdapter() {
        for (int i = 1; i <= 10; i++) {
            String code = "R-" + String.format("%03d", i);
            robotStore.put(code, new RobotState(code, "Farm Robot " + i));
        }
    }

    @Override
    public RobotState loadByCode(String code) {
        return robotStore.get(code);
    }

    @Override
    public List<RobotState> loadAll() {
        return new ArrayList<>(robotStore.values());
    }

    @Override
    public RobotState save(RobotState robotState) {
        robotStore.put(robotState.getCode(), robotState);
        return robotState;
    }
}