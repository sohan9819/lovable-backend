package com.sohan.projects.lovable_clone.dto.subscription;

public record PlanResponse(
        Long id,
        String name,
        String stripePriceId,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Integer maxPreview, // max number of previews allowed per plan
        Boolean unlimitedAi, // unlimited access  to LLM, ignore maxTokenPerDay if true
        String price
) {
}
