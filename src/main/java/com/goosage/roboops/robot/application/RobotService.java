package com.goosage.roboops.robot.application;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.goosage.roboops.robot.domain.Robot;

@Service
public class RobotService {

    private final Map<String, Robot> store = new ConcurrentHashMap<>();

    public RobotService() {
        store.put("R-001", new Robot("R-001", "FarmBot-1", "IDLE"));
        store.put("R-002", new Robot("R-002", "FarmBot-2", "WORKING"));
        store.put("R-003", new Robot("R-003", "FarmBot-3", "CHARGING"));
    }

    public java.util.List<Robot> getRobots() {
        return store.values().stream().toList();
    }

    public Robot getRobotByCode(String code) {
        Robot r = store.get(code);
        if (r == null) {
            throw new IllegalArgumentException("Robot not found: " + code);
        }
        return r;
    }

    public Robot updateStatus(String code, String status) {
        Robot current = getRobotByCode(code);
        Robot updated = new Robot(current.code(), current.name(), status);
        store.put(code, updated);
        return updated;
    }

    public Robot syncStatusFromTelemetry(String code, String status) {
        return updateStatus(code, status);
    }
}