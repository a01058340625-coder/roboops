package com.goosage.roboops.event.adapter.out.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.goosage.roboops.event.domain.EventLog;
import com.goosage.roboops.event.domain.EventType;
import com.goosage.roboops.event.domain.RobotEvent;
import com.goosage.roboops.event.port.out.SaveRobotEventPort;

@Component
public class EventMemoryAdapter implements SaveRobotEventPort {

    private final List<RobotEvent> events = new ArrayList<>();
    private long sequence = 1L;

    @Override
    public void save(RobotEvent event) {
        events.add(event);
    }

    @Override
    public RobotEvent createAndSave(EventType eventType, String robotCode, String message) {
        RobotEvent event = new RobotEvent(sequence++, eventType, robotCode, message);
        events.add(event);
        return event;
    }

    @Override
    public List<EventLog> loadAll() {
        return events.stream()
                .map(RobotEvent::toEventLog)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventLog> loadByRobotCode(String robotCode) {
        return events.stream()
                .filter(e -> e.getRobotCode().equals(robotCode))
                .map(RobotEvent::toEventLog)
                .collect(Collectors.toList());
    }
}