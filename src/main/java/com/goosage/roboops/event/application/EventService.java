package com.goosage.roboops.event.application;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.goosage.roboops.event.domain.EventLog;

@Service
public class EventService {

    private final AtomicLong sequence = new AtomicLong(0);
    private final CopyOnWriteArrayList<EventLog> store = new CopyOnWriteArrayList<>();

    public EventLog publish(String eventType, String robotCode, String message) {
        EventLog event = new EventLog(
                sequence.incrementAndGet(),
                eventType,
                robotCode,
                message
        );
        store.add(event);
        return event;
    }

    public List<EventLog> getEvents() {
        return store;
    }
}