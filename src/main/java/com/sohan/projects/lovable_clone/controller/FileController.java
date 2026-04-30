package com.sohan.projects.lovable_clone.controller;

import com.sohan.projects.lovable_clone.dto.project.FileContentResponse;
import com.sohan.projects.lovable_clone.dto.project.FileNode;
import com.sohan.projects.lovable_clone.service.FileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/files")
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
public class FileController {
    FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileNode>> getFileTree(@PathVariable Long projectId){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(fileService.getFileTree(projectId , userId));
    }

    @GetMapping("/{*path}")
    public ResponseEntity<FileContentResponse> getFile(
            @PathVariable Long projectId,
            @PathVariable String path
    ){
        Long userId = 1L; // TODO : Update later with spring security
        return ResponseEntity.ok(fileService.getFileContent(projectId, path, userId));
    }
}
