package com.nutricare.nutricare_advisor.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nutricare.nutricare_advisor.entity.CategoryDiseaseRule;

public interface CategoryDiseaseRuleRepository extends JpaRepository<CategoryDiseaseRule, Long> {

	List<CategoryDiseaseRule> findByDiseaseDiseaseIdIn(List<Long> diseaseIds);

	List<CategoryDiseaseRule> findByDisease_DiseaseId(Long diseaseId);

	boolean existsByFoodCategoryAndDisease_DiseaseId(String foodCategory, Long diseaseId);

}
