package com.nutricare.nutricare_advisor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nutricare.nutricare_advisor.repository.CategoryDiseaseRuleRepository;
import com.nutricare.nutricare_advisor.entity.CategoryDiseaseRule;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class CategoryDiseaseRuleService {

    private final CategoryDiseaseRuleRepository  repository;

    public List<CategoryDiseaseRule> getRulesForDisease(Long diseaseId) {
        return repository.findByDisease_DiseaseId(diseaseId);
    }
}
