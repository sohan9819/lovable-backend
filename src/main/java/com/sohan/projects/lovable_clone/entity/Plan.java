package com.sohan.projects.lovable_clone.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {
    Long id;

    String name;



    String stripePriceId;
    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews; // max number of previews allowed per plan
    Boolean unlimitedAi; // unlimited access  to LLM, ignore maxTokenPerDay if true

    Boolean active;
}
