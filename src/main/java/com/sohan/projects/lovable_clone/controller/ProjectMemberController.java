package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.sohan.projects.lovable_clone.dto.member.MemberResponse;
import com.sohan.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.sohan.projects.lovable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberController {
    ProjectMemberService projectMemberService;

    @GetMapping
    ResponseEntity<List<MemberResponse>> getProjectMember(@PathVariable Long projectId) {
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody InviteMemberRequest request
    ) {
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request, userId)
        );
    }

    @GetMapping("/invites")
    ResponseEntity<List<MemberResponse>> getInvitedMembers(@PathVariable Long projectId) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getInvitedMembers(projectId, userId));
    }

    @PatchMapping
    ResponseEntity<MemberResponse> acceptMemberInvite(
            @PathVariable Long projectId
    ) {
        Long userId = 2L; // TODO : Update later with spring security
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.acceptMemberInvite(projectId, userId)
        );
    }

    @DeleteMapping
    ResponseEntity<Void> rejectMemberInvite(
            @PathVariable Long projectId
    ) {
        Long userId = 3L; // TODO : Update later with spring security
        projectMemberService.rejectMemberInvite(projectId, userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{memberId}")
    ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRoleRequest request
    ) {
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(
                projectMemberService.updateMemberRole(projectId, memberId, request, userId)
        );
    }

    @DeleteMapping("/{memberId}")
    ResponseEntity<Void> removeMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ) {
        Long userId = 1L; // TODO : Update later with spring security
        projectMemberService.removeProjectMember(projectId, memberId, userId);
        return ResponseEntity.noContent().build();
    }


}
