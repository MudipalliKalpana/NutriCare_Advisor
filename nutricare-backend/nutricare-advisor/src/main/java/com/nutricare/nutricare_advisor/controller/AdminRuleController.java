package com.nutricare.nutricare_advisor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.entity.FoodDiseaseRule;
import com.nutricare.nutricare_advisor.entity.Impact;
import com.nutricare.nutricare_advisor.service.AdminRuleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/rules")
@RequiredArgsConstructor
public class AdminRuleController {

	private final AdminRuleService service;
	
//	POST /api/admin/rules?foodId=1&diseaseId=1&impact=ALLOWED&explanation=Low sugar
//	Authorization: Bearer <ADMIN_TOKEN>
	
	@PostMapping
	public FoodDiseaseRule addRule(@RequestParam Long foodId,
								  @RequestParam Long diseaseId,
								  @RequestParam Impact impact,
								  @RequestParam String explanation) {
		return service.addRule(foodId, diseaseId, impact, explanation);
	}
	
	@GetMapping 
	public java.util.List<FoodDiseaseRule> listRules() {
	    return service.getAllRules();
	}
	
	@PutMapping("/{ruleId}")
	public FoodDiseaseRule updateRule(
	        @PathVariable Long ruleId,
	        @RequestParam Long foodId,
	        @RequestParam Long diseaseId,
	        @RequestParam Impact impact,
	        @RequestParam String explanation) {

	    return service.updateRule(ruleId, foodId, diseaseId, impact, explanation);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity <Void> delete(@PathVariable Long id) {
	    service.deleteRule(id);
	    return ResponseEntity.noContent().build();
	}


	
}
