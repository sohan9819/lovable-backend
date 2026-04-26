package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.sohan.projects.lovable_clone.dto.member.MemberResponse;
import com.sohan.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.sohan.projects.lovable_clone.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMember {
    private final ProjectMemberService projectMemberService;

    @GetMapping
    ResponseEntity<List<MemberResponse>> getProjectMember(@PathVariable Long projectId){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody InviteMemberRequest request
    ){
        Long userId = 1L; // TODO : Update later with spring security
        return  ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId , request , userId)
        );
    }

    @PatchMapping("/{memberId}")
    ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRoleRequest request
    ){
        Long userId = 1L; // TODO : Update later with spring security
        return  ResponseEntity.ok(
                projectMemberService.updateMemberRole(projectId, memberId , request , userId)
        );
    }

    @DeleteMapping("/{memberId}")
    ResponseEntity<Void> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ){
        Long userId = 1L; // TODO : Update later with spring security
        return  ResponseEntity.ok(
                projectMemberService.deleteProjectMember(projectId, memberId, userId)
        );
    }


}
