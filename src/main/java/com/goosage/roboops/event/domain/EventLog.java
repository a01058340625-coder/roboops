package com.goosage.roboops.event.domain;

public record EventLog(
        Long id,
        String eventType,
        String robotCode,
        String message
) {
}