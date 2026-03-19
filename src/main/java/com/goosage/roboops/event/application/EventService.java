package com.goosage.roboops.event.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.goosage.roboops.event.domain.EventLog;
import com.goosage.roboops.event.port.out.SaveRobotEventPort;

@Service
public class EventService {

    private final SaveRobotEventPort saveRobotEventPort;

    public EventService(SaveRobotEventPort saveRobotEventPort) {
        this.saveRobotEventPort = saveRobotEventPort;
    }

    public List<EventLog> getAll() {
        return saveRobotEventPort.loadAll();
    }

    public List<EventLog> getByRobotCode(String robotCode) {
        return saveRobotEventPort.loadByRobotCode(robotCode);
    }
}