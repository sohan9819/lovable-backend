package com.sohan.projects.lovable_clone.service;

import com.sohan.projects.lovable_clone.dto.subscription.PlanResponse;
import com.sohan.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
