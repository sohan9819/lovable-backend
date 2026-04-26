package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.project.ProjectRequest;
import com.sohan.projects.lovable_clone.dto.project.ProjectResponse;
import com.sohan.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.sohan.projects.lovable_clone.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<ProjectSummaryResponse> getMyProjects(){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectService.getUserProjectById(id , userId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createUserProject(request , userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody ProjectRequest request ){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectService.updateUserProject(id, request , userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectService.softDeleteUserProject(id, userId));
    }
}
