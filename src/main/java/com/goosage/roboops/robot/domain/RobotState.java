package com.goosage.roboops.robot.domain;

import java.time.LocalDateTime;

public class RobotState {

    private final String code;
    private final String name;

    private int batteryLevel;
    private String location;
    private String currentTask;
    private RobotStatus status;
    private HealthState healthState;
    private RecommendedAction recommendedAction;
    private LocalDateTime lastTelemetryAt;
    private String lastErrorCode;

    public RobotState(String code, String name) {
        this.code = code;
        this.name = name;
        this.batteryLevel = 100;
        this.location = "UNKNOWN";
        this.currentTask = "NONE";
        this.status = RobotStatus.IDLE;
        this.healthState = HealthState.NORMAL;
        this.recommendedAction = RecommendedAction.CONTINUE_WORK;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public String getLocation() {
        return location;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public RobotStatus getStatus() {
        return status;
    }

    public HealthState getHealthState() {
        return healthState;
    }

    public RecommendedAction getRecommendedAction() {
        return recommendedAction;
    }

    public LocalDateTime getLastTelemetryAt() {
        return lastTelemetryAt;
    }

    public String getLastErrorCode() {
        return lastErrorCode;
    }

    public void updateTelemetry(int batteryLevel,
                                String location,
                                String currentTask,
                                LocalDateTime observedAt,
                                String errorCode) {
        this.batteryLevel = batteryLevel;
        this.location = location;
        this.currentTask = currentTask;
        this.lastTelemetryAt = observedAt;
        this.lastErrorCode = errorCode;
    }

    public void applyHealth(RobotStatus status,
                            HealthState healthState,
                            RecommendedAction recommendedAction) {
        this.status = status;
        this.healthState = healthState;
        this.recommendedAction = recommendedAction;
    }

    public Robot toRobot() {
        return new Robot(code, name, status.name());
    }
}