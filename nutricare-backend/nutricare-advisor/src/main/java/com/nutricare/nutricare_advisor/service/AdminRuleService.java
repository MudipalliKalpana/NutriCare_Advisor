package com.nutricare.nutricare_advisor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutricare.nutricare_advisor.entity.Disease;
import com.nutricare.nutricare_advisor.entity.Food;
import com.nutricare.nutricare_advisor.entity.FoodDiseaseRule;
import com.nutricare.nutricare_advisor.entity.Impact;
import com.nutricare.nutricare_advisor.exception.ResourceNotFoundException;
import com.nutricare.nutricare_advisor.repository.DiseaseRepository;
import com.nutricare.nutricare_advisor.repository.FoodDiseaseRuleRepository;
import com.nutricare.nutricare_advisor.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminRuleService {

	private final FoodRepository foodRepo;
	private final DiseaseRepository diseaseRepo;
	private final FoodDiseaseRuleRepository ruleRepo;
	private final AdminAuditService adminAuditService;
	
	public FoodDiseaseRule addRule(Long foodId, Long diseaseId, Impact impact, String explanation) {
		// 🚫 DUPLICATE CHECK
	    if (ruleRepo.existsByFoodFoodIdAndDiseaseDiseaseId(foodId, diseaseId)) {
	        throw new IllegalArgumentException(
	            "Rule already exists for this Food and Disease"
	        );
	    }
		
		Food food= foodRepo.findById(foodId)
				.orElseThrow(()->new ResourceNotFoundException("Food not found"));
		
		Disease disease=diseaseRepo.findById(diseaseId)
				.orElseThrow(()->new ResourceNotFoundException("Disease not found"));
		
		FoodDiseaseRule rule=new FoodDiseaseRule();
		rule.setFood(food);
		rule.setDisease(disease);;
		rule.setImpact(impact);
		rule.setExplanation(explanation);
		
		FoodDiseaseRule savedRule = ruleRepo.save(rule);

		adminAuditService.log(
		    "admin@nutricare.com", // later replace with JWT username
		    "ADD_RULE",
		    "RULE",
		    savedRule.getRuleId()
		);

		return savedRule;

 	}
	
	public java.util.List<FoodDiseaseRule> getAllRules() {
	    return ruleRepo.findAll();
	}
	
	public FoodDiseaseRule updateRule(
	        Long ruleId,
	        Long foodId,
	        Long diseaseId,
	        Impact impact,
	        String explanation) {

	    FoodDiseaseRule rule = ruleRepo.findById(ruleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

	    Food food = foodRepo.findById(foodId)
	            .orElseThrow(() -> new ResourceNotFoundException("Food not found"));

	    Disease disease = diseaseRepo.findById(diseaseId)
	            .orElseThrow(() -> new ResourceNotFoundException("Disease not found"));

	    rule.setFood(food);
	    rule.setDisease(disease);
	    rule.setImpact(impact);
	    rule.setExplanation(explanation);

	    return ruleRepo.save(rule);
	}

	public void deleteRule(Long ruleId) {
	    if (!ruleRepo.existsById(ruleId)) {
	        throw new ResourceNotFoundException("Rule not found");
	    }
	    ruleRepo.deleteById(ruleId);
	}

}
