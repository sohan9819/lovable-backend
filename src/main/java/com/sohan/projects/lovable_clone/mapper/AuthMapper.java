package com.sohan.projects.lovable_clone.mapper;

import com.sohan.projects.lovable_clone.dto.auth.SignupRequest;
import com.sohan.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.sohan.projects.lovable_clone.dto.project.ProjectResponse;
import com.sohan.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    UserProfileResponse toUserProfileResponse(User user);
    User toUserEntity(SignupRequest signupRequest);
}
