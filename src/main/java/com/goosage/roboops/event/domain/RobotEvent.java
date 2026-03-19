package com.goosage.roboops.event.domain;

public class RobotEvent {

    private final Long id;
    private final EventType eventType;
    private final String robotCode;
    private final String message;

    public RobotEvent(Long id, EventType eventType, String robotCode, String message) {
        this.id = id;
        this.eventType = eventType;
        this.robotCode = robotCode;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public String getMessage() {
        return message;
    }

    public EventLog toEventLog() {
        return new EventLog(id, eventType.name(), robotCode, message);
    }
}