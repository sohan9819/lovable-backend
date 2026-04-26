package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.subscription.*;
import com.sohan.projects.lovable_clone.service.PlanService;
import com.sohan.projects.lovable_clone.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BillingController {
    private final PlanService planService;
    private final SubscriptionService subscriptionService;

    @GetMapping("/api/plans")
    public ResponseEntity<List<PlanResponse>> getAllPlans(){
        return ResponseEntity.ok(planService.getAllActivePlans());
    }

    @GetMapping("/api/me/subscription")
    public ResponseEntity<SubscriptionResponse> getMySubscription(){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(subscriptionService.getCurrentSubscription(userId));
    }

    @GetMapping("/api/stripe/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutResponse(
            @RequestBody CheckoutRequest request
            ){
        Long userId = 1L; // TODO : Update later with spring security
        return  ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createCheckoutSessionUrl(request , userId));
    }

    @PostMapping("/api/stripe/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal(){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
    }

}
