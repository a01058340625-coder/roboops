package com.goosage.roboops.robot.api.dto;

public class RobotCommandResponse {

    private final String robotCode;
    private final String command;
    private final String result;
    private final String message;

    public RobotCommandResponse(String robotCode, String command, String result, String message) {
        this.robotCode = robotCode;
        this.command = command;
        this.result = result;
        this.message = message;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public String getCommand() {
        return command;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}