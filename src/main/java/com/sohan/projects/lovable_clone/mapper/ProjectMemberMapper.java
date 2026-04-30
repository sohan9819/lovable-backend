package com.sohan.projects.lovable_clone.mapper;

import com.sohan.projects.lovable_clone.dto.member.MemberResponse;
import com.sohan.projects.lovable_clone.entity.ProjectMember;
import com.sohan.projects.lovable_clone.entity.User;
import com.sohan.projects.lovable_clone.enums.ProjectRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "name", source = "user.name"),
    })
    MemberResponse toMemberResponseFromMember(ProjectMember member);
}
