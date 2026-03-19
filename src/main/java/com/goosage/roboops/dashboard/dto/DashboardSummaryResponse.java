package com.goosage.roboops.dashboard.dto;

public class DashboardSummaryResponse {

    private final int totalRobots;
    private final int workingRobots;
    private final int chargingRobots;
    private final int warningRobots;
    private final int errorRobots;
    private final int offlineRobots;
    private final int lowBatteryCount;
    private final String recommendedFocus;

    public DashboardSummaryResponse(int totalRobots,
                                    int workingRobots,
                                    int chargingRobots,
                                    int warningRobots,
                                    int errorRobots,
                                    int offlineRobots,
                                    int lowBatteryCount,
                                    String recommendedFocus) {
        this.totalRobots = totalRobots;
        this.workingRobots = workingRobots;
        this.chargingRobots = chargingRobots;
        this.warningRobots = warningRobots;
        this.errorRobots = errorRobots;
        this.offlineRobots = offlineRobots;
        this.lowBatteryCount = lowBatteryCount;
        this.recommendedFocus = recommendedFocus;
    }

    public int getTotalRobots() {
        return totalRobots;
    }

    public int getWorkingRobots() {
        return workingRobots;
    }

    public int getChargingRobots() {
        return chargingRobots;
    }

    public int getWarningRobots() {
        return warningRobots;
    }

    public int getErrorRobots() {
        return errorRobots;
    }

    public int getOfflineRobots() {
        return offlineRobots;
    }

    public int getLowBatteryCount() {
        return lowBatteryCount;
    }

    public String getRecommendedFocus() {
        return recommendedFocus;
    }
}