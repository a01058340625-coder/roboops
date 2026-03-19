package com.goosage.roboops.event.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.event.application.EventService;
import com.goosage.roboops.event.domain.EventLog;

@RestController
public class EventQueryController {

    private final EventService eventService;

    public EventQueryController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/api/events")
    public List<EventLog> getEvents() {
        return eventService.getEvents();
    }
}