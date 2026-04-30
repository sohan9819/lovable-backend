package com.sohan.projects.lovable_clone.dto.member;

import com.sohan.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String email,
        String name,
        ProjectRole role,
        Instant invitedAt,
        Instant acceptedAt
) {
}
