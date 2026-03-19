package com.goosage.roboops.telemetry.application;

import com.goosage.roboops.robot.domain.HealthState;
import com.goosage.roboops.robot.domain.RecommendedAction;
import com.goosage.roboops.robot.domain.RobotStatus;

public class RobotHealthDecision {

    private final RobotStatus status;
    private final HealthState healthState;
    private final RecommendedAction recommendedAction;
    private final String message;

    public RobotHealthDecision(RobotStatus status,
                               HealthState healthState,
                               RecommendedAction recommendedAction,
                               String message) {
        this.status = status;
        this.healthState = healthState;
        this.recommendedAction = recommendedAction;
        this.message = message;
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

    public String getMessage() {
        return message;
    }
}