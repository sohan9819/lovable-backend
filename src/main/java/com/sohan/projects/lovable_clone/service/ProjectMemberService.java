package com.sohan.projects.lovable_clone.service;

import com.sohan.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.sohan.projects.lovable_clone.dto.member.MemberResponse;
import com.sohan.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);
    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);
    List<MemberResponse> getInvitedMembers(Long projectId, Long userId);
    MemberResponse acceptMemberInvite(Long projectId, Long userId);
    void rejectMemberInvite(Long projectId, Long userId);
    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);
    void removeProjectMember(Long projectId, Long memberId, Long userId);
}
