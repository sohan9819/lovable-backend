package com.sohan.projects.lovable_clone.service;

import com.sohan.projects.lovable_clone.dto.project.ProjectRequest;
import com.sohan.projects.lovable_clone.dto.project.ProjectResponse;
import com.sohan.projects.lovable_clone.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects(Long userId);
    ProjectResponse getUserProjectById(Long id, Long userId);
    ProjectResponse createUserProject(ProjectRequest request, Long userId);
    ProjectResponse updateUserProject(Long id, ProjectRequest request, Long userId);
    void softDeleteUserProject(Long id, Long userId);
}
