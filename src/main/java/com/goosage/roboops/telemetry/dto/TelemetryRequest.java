package com.goosage.roboops.telemetry.dto;

import java.time.LocalDateTime;

public class TelemetryRequest {

    private String robotCode;
    private int batteryLevel;
    private String location;
    private String currentTask;
    private String errorCode;
    private LocalDateTime observedAt;

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getObservedAt() {
        return observedAt;
    }

    public void setObservedAt(LocalDateTime observedAt) {
        this.observedAt = observedAt;
    }
}