package com.sohan.projects.lovable_clone.service;

import com.sohan.projects.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.sohan.projects.lovable_clone.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);
    PlanLimitsResponse getCurrentSubscriptionOfUser(Long userId);
}
