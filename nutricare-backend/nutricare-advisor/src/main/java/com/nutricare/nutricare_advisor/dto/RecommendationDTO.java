package com.nutricare.nutricare_advisor.dto;

import java.util.List;
import java.util.Map;

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
    private String impact;
    private String explanation;
    private boolean autoLimited;

    // 🔹 MongoDB Nutrition Data
    private Map<String, Object> nutrients;
    private List<String> benefits;
    private List<String> warnings;

    // 🔹 EXISTING constructor (DO NOT REMOVE)
    public RecommendationDTO(
            String foodName,
            String impact,
            String explanation,
            boolean autoLimited
    ) {
        this.foodName = foodName;
        this.impact = impact;
        this.explanation = explanation;
        this.autoLimited = autoLimited;
    }
}
