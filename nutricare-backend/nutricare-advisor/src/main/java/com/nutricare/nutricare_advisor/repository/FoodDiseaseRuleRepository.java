package com.nutricare.nutricare_advisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricare.nutricare_advisor.entity.FoodDiseaseRule;
import com.nutricare.nutricare_advisor.entity.Impact;

public interface FoodDiseaseRuleRepository extends JpaRepository<FoodDiseaseRule, Long>{
	List<FoodDiseaseRule> findByDiseaseDiseaseIdIn(List<Long> diseaseIds);
	List<FoodDiseaseRule> findByDiseaseDiseaseIdAndImpact(Long diseaseId, Impact impact);
	List<FoodDiseaseRule> findByDiseaseDiseaseIdAndImpactIn(Long diseaseId, List<Impact> impacts);
	boolean existsByFoodFoodIdAndDiseaseDiseaseId(Long foodId, Long diseaseId);

}