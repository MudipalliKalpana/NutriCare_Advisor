package com.nutricare.nutricare_advisor.service;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nutricare.nutricare_advisor.dto.RecommendationDTO;
import com.nutricare.nutricare_advisor.entity.*;
import com.nutricare.nutricare_advisor.mongo.FoodNutrition;
import com.nutricare.nutricare_advisor.mongo.FoodNutritionRepository;
import com.nutricare.nutricare_advisor.repository.*;

@Service
@RequiredArgsConstructor
public class RecommendationService {

	 private static final Logger log =
		        LoggerFactory.getLogger(RecommendationService.class);
	
    private final FoodDiseaseRuleRepository ruleRepository;
    private final DiseaseRepository diseaseRepository;
    private final CategoryDiseaseRuleRepository categoryRuleRepo;
    private final FoodNutritionRepository nutritionRepo;
    
    /* =========================
       MAIN ENTRY METHOD
       ========================= */
    public List<RecommendationDTO> recommendFoods(List<Long> diseaseIds) {

    		if (diseaseIds.stream().anyMatch(Objects::isNull)) {
		    throw new IllegalArgumentException("Invalid disease ID provided");
		}
    	
        if (diseaseIds == null || diseaseIds.isEmpty()) {
        	log.warn("No diseases selected, returning default SAFE recommendation");
            return List.of(
                new RecommendationDTO(
                    "Water",
                    "SAFE",
                    "Essential and safe for all conditions",
                    false
                )
            );
        }

        if (diseaseIds.size() == 1) {
            return handleSingleDisease(diseaseIds.get(0));
        }

        return handleMultipleDiseases(diseaseIds);
    }

    /* =========================
       SINGLE DISEASE LOGIC
       ========================= */
    private List<RecommendationDTO> handleSingleDisease(Long diseaseId) {

        Map<Long, FoodDiseaseRule> bestRulePerFood = new HashMap<>();

        ruleRepository.findByDiseaseDiseaseIdAndImpactIn(
            diseaseId,
            List.of(Impact.ALLOWED, Impact.LIMIT)
        ).forEach(rule -> {

            bestRulePerFood.merge(
                rule.getFood().getFoodId(),
                rule,
                (oldRule, newRule) ->
                    newRule.getImpact() == Impact.LIMIT ? newRule : oldRule
            );
        });

        List<RecommendationDTO> result =
            bestRulePerFood.values().stream()
                .map(r -> new RecommendationDTO(
                    r.getFood().getFoodName(),
                    r.getImpact().name(),
                    r.getImpact() == Impact.LIMIT
                        ? withDisclaimer(r.getExplanation())
                        : r.getExplanation(),
                    r.getImpact() == Impact.LIMIT
                ))
                .collect(Collectors.toList());

        addWater(result);
        return result;
    }

    /* =========================
       MULTI DISEASE LOGIC
       ========================= */
    private List<RecommendationDTO> handleMultipleDiseases(List<Long> diseaseIds) {

        List<Set<Long>> allowedFoodSets = new ArrayList<>();
        List<Set<Long>> limitFoodSets = new ArrayList<>();

        for (Long diseaseId : diseaseIds) {

            allowedFoodSets.add(
                ruleRepository
                    .findByDiseaseDiseaseIdAndImpact(diseaseId, Impact.ALLOWED)
                    .stream()
                    .map(r -> r.getFood().getFoodId())
                    .collect(Collectors.toSet())
            );

            limitFoodSets.add(
                ruleRepository
                    .findByDiseaseDiseaseIdAndImpact(diseaseId, Impact.LIMIT)
                    .stream()
                    .map(r -> r.getFood().getFoodId())
                    .collect(Collectors.toSet())
            );
        }

        Set<Long> commonAllowed = intersectSets(allowedFoodSets);
        Set<Long> commonLimit   = intersectSets(limitFoodSets);

        // LIMIT overrides ALLOWED
        commonAllowed.removeAll(commonLimit);

     // 1️⃣ If ALLOWED foods exist → return ONLY ALLOWED
        if (!commonAllowed.isEmpty()) {
            return buildResponse(commonAllowed, diseaseIds);
        }

        // 2️⃣ If no ALLOWED but LIMIT foods exist → return ONLY LIMIT
        if (!commonLimit.isEmpty()) {
            return buildResponse(commonLimit, diseaseIds);
        }

        // 3️⃣ Absolute fallback → Water
        List<RecommendationDTO> result = new ArrayList<>();
        addWater(result);
        return result;

    }

    /* =========================
       BUILD RESPONSE (FINAL)
       ========================= */
    private List<RecommendationDTO> buildResponse(
    	    Set<Long> foodIds,
    	    List<Long> diseaseIds
    	) {

    	    Map<String, RecommendationDTO> finalMap = new LinkedHashMap<>();
    	    Map<String, Set<String>> explanationMap = new HashMap<>();

    	    List<CategoryDiseaseRule> categoryRules =
    	        categoryRuleRepo.findByDiseaseDiseaseIdIn(diseaseIds);

    	    ruleRepository.findByDiseaseDiseaseIdIn(diseaseIds).stream()
    	        .filter(r -> foodIds.contains(r.getFood().getFoodId()))
    	        .forEach(r -> {

    	            Food food = r.getFood();
    	            String foodName = food.getFoodName();

    	            Impact finalImpact = r.getImpact();
    	            boolean autoLimited = false;

    	            if (isHighRiskCategory(food, categoryRules)) {
    	                finalImpact = Impact.LIMIT;
    	                autoLimited = true;
    	            }

    	            String explanation =
    	            	    finalImpact == Impact.LIMIT
    	            	        ? withDisclaimer(r.getExplanation())
    	            	        : r.getExplanation();

    	            	/* =========================
    	            	   MONGODB NUTRITION MERGE
    	            	   ========================= */
    	            	Optional<FoodNutrition> nutritionOpt =
    	            	        nutritionRepo.findByFoodNameIgnoreCase(foodName);

    	            	if (nutritionOpt.isPresent()) {
    	            	    FoodNutrition nutrition = nutritionOpt.get();

    	            	    if (nutrition.getBenefits() != null && !nutrition.getBenefits().isEmpty()) {
    	            	        explanation += "; Benefits: " +
    	            	                String.join(", ", nutrition.getBenefits());
    	            	    }

    	            	    if (nutrition.getWarningStrings() != null &&
    	            	        !nutrition.getWarningStrings().isEmpty()) {
    	            	        explanation += "; Warnings: " +
    	            	                String.join(", ", nutrition.getWarningStrings());
    	            	    }
    	            	}
    	            	// 🔍 TEMP DEBUG LOG
    	            	log.info("Final explanation for {} : {}", foodName, explanation);

    	            	explanationMap
    	            	    .computeIfAbsent(foodName, k -> new LinkedHashSet<>())
    	            	    .add(explanation);


    	            RecommendationDTO existing = finalMap.get(foodName);

    	            if (existing == null || finalImpact == Impact.LIMIT) {
    	                finalMap.put(
    	                    foodName,
    	                    new RecommendationDTO(
    	                        foodName,
    	                        finalImpact.name(),
    	                        "",   // placeholder
    	                        autoLimited
    	                    )
    	                );
    	            }
    	        });

    	    // Merge explanations
    	    finalMap.values().forEach(dto -> {
    	        Set<String> explanations = explanationMap.get(dto.getFoodName());
    	        String combined = String.join("; ", explanations);

    	        if (dto.isAutoLimited()) {
    	            combined += "; Auto-limited due to food category risk";
    	        }

    	        dto.setExplanation(combined);
    	    });

    	    return new ArrayList<>(finalMap.values());
    	}

    /* =========================
       UTILITIES
       ========================= */
    private Set<Long> intersectSets(List<Set<Long>> sets) {
        if (sets.isEmpty()) return new HashSet<>();

        Set<Long> result = new HashSet<>(sets.get(0));
        for (int i = 1; i < sets.size(); i++) {
            result.retainAll(sets.get(i));
        }
        return result;
    }

    private void addWater(List<RecommendationDTO> result) {
        result.add(
            new RecommendationDTO(
                "Water",
                "SAFE",
                "Essential and safe for all conditions",
                false
            )
        );
    }

    private String withDisclaimer(String explanation) {
        return explanation + " (Consume in moderation)";
    }

    private boolean isHighRiskCategory(
        Food food,
        List<CategoryDiseaseRule> categoryRules
    ) {
        return categoryRules.stream().anyMatch(r ->
            r.getFoodCategory().equalsIgnoreCase(food.getCategory()) &&
            r.getImpact() == Impact.LIMIT
        );
    }

    /* =========================
       DISEASE LIST
       ========================= */
    public List<Disease> listDiseases() {
//    	log.info("Generating recommendations for diseases: {}", diseaseIds);
        return diseaseRepository.findAll()
            .stream()
            .filter(Disease::getIsActive)
            .collect(Collectors.toList());
    }
}
