package com.sohan.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile {
    Long id;

    Project project;

    String path;

    String minioObjectKey;

    User createdBy;
    User updatedBy;

    Instant createdAt;
    Instant deletedAt;
}
