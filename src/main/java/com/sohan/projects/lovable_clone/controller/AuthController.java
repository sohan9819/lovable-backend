package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.auth.AuthResponse;
import com.sohan.projects.lovable_clone.dto.auth.LoginRequest;
import com.sohan.projects.lovable_clone.dto.auth.SignupRequest;
import com.sohan.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.sohan.projects.lovable_clone.service.AuthService;
import com.sohan.projects.lovable_clone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userServicer;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(SignupRequest request){
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        Long userId = 1L;
        return ResponseEntity.ok(userServicer.getProfile(userId));
    }
}
