package com.goosage.roboops.dashboard.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goosage.roboops.dashboard.dto.DashboardSummaryResponse;
import com.goosage.roboops.dashboard.port.in.GetDashboardSummaryUseCase;
import com.goosage.roboops.robot.application.RobotService;
import com.goosage.roboops.robot.domain.HealthState;
import com.goosage.roboops.robot.domain.RobotState;
import com.goosage.roboops.robot.domain.RobotStatus;

@Service
public class DashboardService implements GetDashboardSummaryUseCase {

    private final RobotService robotService;

    public DashboardService(RobotService robotService) {
        this.robotService = robotService;
    }

    @Override
    public DashboardSummaryResponse getSummary() {
        List<RobotState> robots = robotService.getAllStates();

        int total = robots.size();
        int working = 0;
        int charging = 0;
        int warning = 0;
        int error = 0;
        int offline = 0;
        int lowBattery = 0;

        String recommendedFocus = "All robots stable";

        for (RobotState robot : robots) {
            if (robot.getStatus() == RobotStatus.WORKING) working++;
            if (robot.getStatus() == RobotStatus.CHARGING) charging++;
            if (robot.getStatus() == RobotStatus.WARNING) warning++;
            if (robot.getStatus() == RobotStatus.ERROR) error++;
            if (robot.getStatus() == RobotStatus.OFFLINE) offline++;
            if (robot.getHealthState() == HealthState.LOW_BATTERY) lowBattery++;

            if (robot.getStatus() == RobotStatus.ERROR) {
                recommendedFocus = "Check " + robot.getCode() + " immediately";
            } else if (robot.getStatus() == RobotStatus.OFFLINE) {
                recommendedFocus = "Reconnect or inspect " + robot.getCode();
            } else if (robot.getHealthState() == HealthState.LOW_BATTERY) {
                recommendedFocus = "Send " + robot.getCode() + " to charger";
            }
        }

        return new DashboardSummaryResponse(
                total, working, charging, warning, error, offline, lowBattery, recommendedFocus
        );
    }
}