package com.sohan.projects.lovable_clone.service.impl;

import com.sohan.projects.lovable_clone.dto.project.ProjectRequest;
import com.sohan.projects.lovable_clone.dto.project.ProjectResponse;
import com.sohan.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.sohan.projects.lovable_clone.entity.Project;
import com.sohan.projects.lovable_clone.entity.ProjectMember;
import com.sohan.projects.lovable_clone.entity.ProjectMemberId;
import com.sohan.projects.lovable_clone.entity.User;
import com.sohan.projects.lovable_clone.enums.ProjectRole;
import com.sohan.projects.lovable_clone.error.ResourceNotFoundException;
import com.sohan.projects.lovable_clone.mapper.ProjectMapper;
import com.sohan.projects.lovable_clone.repository.ProjectMemberRepository;
import com.sohan.projects.lovable_clone.repository.ProjectRepository;
import com.sohan.projects.lovable_clone.repository.UserRepository;
import com.sohan.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    UserRepository userRepository;
    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    ProjectMapper projectMapper;

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
//        return projectRepository.findAllAccessibleByUser(userId).stream().map(projectMapper::toProjectSummaryResponse).collect(Collectors.toList());
        var projects = projectRepository.findAllAccessibleProjectByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createUserProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , userId.toString()));

//      Create User Project
        Project project = Project.builder().name(request.name()).owner(owner).isPublic(false).build();
        project = projectRepository.save(project);

//      Add User as the Project Member with the role OWNER
        ProjectMemberId projectMemberId = new ProjectMemberId(userId , project.getId());
        ProjectMember projectMember = ProjectMember
                .builder()
                .id(projectMemberId)
                .project(project)
                .user(owner)
                .role(ProjectRole.OWNER)
                .invitedAt(Instant.now())
                .acceptedAt(Instant.now())
                .build();
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse((project));
    }

    @Override
    public ProjectResponse updateUserProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to update name of this project");
        }
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse((project));
    }

    @Override
    public void softDeleteUserProject(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to delete this project");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // INTERNAL FUNCTIONS

    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow(() -> new ResourceNotFoundException("Project" , id.toString()));
    }

}
