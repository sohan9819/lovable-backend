package com.sohan.projects.lovable_clone.entity;

import com.sohan.projects.lovable_clone.enums.SubscriptionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {
    Long id;

    User user;
    Plan plan;

    SubscriptionStatus status;

    String stripeCustomerId;
    String stripeSubscriptionId;

    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Instant cancelAtPeriodEnd;

    Instant createdAt;
    Instant updatedAt;
}
