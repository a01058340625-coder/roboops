package com.goosage.roboops.common;

public record ErrorResponse(
        String code,
        String message
) {
}