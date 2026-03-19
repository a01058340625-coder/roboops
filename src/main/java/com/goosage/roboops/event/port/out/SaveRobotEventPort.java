package com.goosage.roboops.event.port.out;

import java.util.List;

import com.goosage.roboops.event.domain.EventLog;
import com.goosage.roboops.event.domain.EventType;
import com.goosage.roboops.event.domain.RobotEvent;

public interface SaveRobotEventPort {

    void save(RobotEvent event);

    RobotEvent createAndSave(EventType eventType, String robotCode, String message);

    List<EventLog> loadAll();

    List<EventLog> loadByRobotCode(String robotCode);
}