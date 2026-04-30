package com.sohan.projects.lovable_clone.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults( level = AccessLevel.PRIVATE)
public class ProjectMemberId implements Serializable {
    Long projectId;
    Long userId;
}
