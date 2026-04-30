package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.sohan.projects.lovable_clone.dto.subscription.UsageTodayResponse;
import com.sohan.projects.lovable_clone.service.UsageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage")
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
public class UsageController {
    UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponse> getTodayUsage(){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(usageService.getTodayUsageOfUser(userId));
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitsResponse> getPlanLimits(){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(usageService.getCurrentSubscriptionOfUser(userId));
    }
}
