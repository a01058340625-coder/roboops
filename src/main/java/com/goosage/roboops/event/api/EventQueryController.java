package com.goosage.roboops.event.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.event.application.EventService;
import com.goosage.roboops.event.domain.EventLog;

@RestController
@RequestMapping("/api/events")
public class EventQueryController {

    private final EventService eventService;

    public EventQueryController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventLog> getEvents(@RequestParam(required = false) String robotCode) {
        if (robotCode == null || robotCode.isBlank()) {
            return eventService.getAll();
        }
        return eventService.getByRobotCode(robotCode);
    }
}