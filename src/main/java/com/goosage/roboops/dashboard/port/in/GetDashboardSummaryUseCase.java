package com.goosage.roboops.dashboard.port.in;

import com.goosage.roboops.dashboard.dto.DashboardSummaryResponse;

public interface GetDashboardSummaryUseCase {

    DashboardSummaryResponse getSummary();
}