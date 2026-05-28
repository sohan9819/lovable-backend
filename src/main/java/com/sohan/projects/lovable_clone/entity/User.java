package com.sohan.projects.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity {
    String email;
    String password;
    String name;

    String avatarUrl;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<Project> projects = new HashSet<Project>(); // inverse side
}
