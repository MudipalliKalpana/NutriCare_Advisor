package com.nutricare.nutricare_advisor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDTO {

    private String foodName;

    /**
     * ALLOWED | LIMIT | AVOID | SAFE
     */
    private String impact;

    /**
     * Human-readable explanation
     * Example:
     * "Low sugar; Auto-limited due to food category risk (Consume in moderation)"
     */
    private String explanation;
    
 // 🔥 NEW (UI badge support)
    private boolean autoLimited;
}
