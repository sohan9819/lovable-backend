package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.project.ProjectRequest;
import com.sohan.projects.lovable_clone.dto.project.ProjectResponse;
import com.sohan.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.sohan.projects.lovable_clone.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
public class ProjectController {

    ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectService.getUserProjectById(id , userId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createUserProject(request , userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest request ){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(projectService.updateUserProject(id, request , userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        Long userId = 1L; // TODO : Update later with spring security
        projectService.softDeleteUserProject(id, userId);
        return ResponseEntity.noContent().build();
    }
}
