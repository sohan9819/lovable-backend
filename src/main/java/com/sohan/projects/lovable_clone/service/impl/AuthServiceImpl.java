package com.sohan.projects.lovable_clone.service.impl;

import com.sohan.projects.lovable_clone.dto.auth.AuthResponse;
import com.sohan.projects.lovable_clone.dto.auth.LoginRequest;
import com.sohan.projects.lovable_clone.dto.auth.SignupRequest;
import com.sohan.projects.lovable_clone.entity.User;
import com.sohan.projects.lovable_clone.error.BadRequestException;
import com.sohan.projects.lovable_clone.mapper.AuthMapper;
import com.sohan.projects.lovable_clone.repository.UserRepository;
import com.sohan.projects.lovable_clone.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
@Transactional
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    AuthMapper authMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByEmail(request.email()).ifPresent(user -> {
            throw new BadRequestException("User already exists with email: " + request.email());
        });
        User user = authMapper.toUserEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return new AuthResponse("dummy" , authMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
