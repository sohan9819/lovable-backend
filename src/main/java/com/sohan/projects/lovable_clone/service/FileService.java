package com.sohan.projects.lovable_clone.service;

import com.sohan.projects.lovable_clone.dto.project.FileContentResponse;
import com.sohan.projects.lovable_clone.dto.project.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);
    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
