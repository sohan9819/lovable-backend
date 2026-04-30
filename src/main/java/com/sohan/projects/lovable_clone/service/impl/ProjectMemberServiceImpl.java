package com.sohan.projects.lovable_clone.service.impl;

import com.sohan.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.sohan.projects.lovable_clone.dto.member.MemberResponse;
import com.sohan.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.sohan.projects.lovable_clone.entity.Project;
import com.sohan.projects.lovable_clone.entity.ProjectMember;
import com.sohan.projects.lovable_clone.entity.ProjectMemberId;
import com.sohan.projects.lovable_clone.entity.User;
import com.sohan.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.sohan.projects.lovable_clone.repository.ProjectMemberRepository;
import com.sohan.projects.lovable_clone.repository.ProjectRepository;
import com.sohan.projects.lovable_clone.repository.UserRepository;
import com.sohan.projects.lovable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {
    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    UserRepository userRepository;
    ProjectMemberMapper projectMemberMapper;


    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Not allowed for a user { userId:" + userId + " } not a member of this project ");
        }
        return projectMemberRepository.findByIdProjectIdAndAcceptedAtIsNotNull(projectId)
                .stream()
                .map(projectMemberMapper::toMemberResponseFromMember)
                .toList();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not allowed to invite member");
        }

        // "invitee" - the person who has been invited
        // "inviter" - the person who is inviting
        User invitee = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("Not allowed to invite member"));

        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
        if (projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember member = ProjectMember
                .builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .role(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);

        return projectMemberMapper.toMemberResponseFromMember(member);
    }

    @Override
    public List<MemberResponse> getInvitedMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not allowed to check invited members");
        }
        return projectMemberRepository.findByIdProjectIdAndAcceptedAtIsNull(projectId)
                .stream()
                .map(projectMemberMapper::toMemberResponseFromMember)
                .toList();
    }

    @Override
    public MemberResponse acceptMemberInvite(Long projectId, Long userId) {
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId)
                .orElseThrow(() -> new RuntimeException("Project Member invite not found for user : " + userId));
        if (projectMember.getAcceptedAt() != null) {
            throw new RuntimeException("Already accepted the member invite");
        }
        projectMember.setAcceptedAt(Instant.now());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toMemberResponseFromMember(projectMember);
    }

    @Override
    public void rejectMemberInvite(Long projectId, Long userId) {
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, userId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId)
                .orElseThrow(() -> new RuntimeException("Project Member Invite not found for user : " + userId));
        if (projectMember.getAcceptedAt() != null) {
            throw new RuntimeException("Already accepted the member invite");
        }
        projectMemberRepository.deleteById(projectMemberId);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not allowed to update member");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not allowed to remove member");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if (!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Cannot remove once again");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }

    // INTERNAL FUNCTIONS

    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow(
                () -> new RuntimeException("Project not accessible for user : " + userId)
        );
    }
}
