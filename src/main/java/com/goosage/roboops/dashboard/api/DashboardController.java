package com.goosage.roboops.dashboard.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goosage.roboops.dashboard.dto.DashboardSummaryResponse;
import com.goosage.roboops.dashboard.port.in.GetDashboardSummaryUseCase;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final GetDashboardSummaryUseCase getDashboardSummaryUseCase;

    public DashboardController(GetDashboardSummaryUseCase getDashboardSummaryUseCase) {
        this.getDashboardSummaryUseCase = getDashboardSummaryUseCase;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary() {
        return getDashboardSummaryUseCase.getSummary();
    }
}