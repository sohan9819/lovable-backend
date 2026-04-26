package com.sohan.projects.lovable_clone.dto.auth;

public record SignupRequest(
        String email,
        String name,
        String password
) {
}
