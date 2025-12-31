package com.nutricare.nutricare_advisor.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.entity.CategoryDiseaseRule;
import com.nutricare.nutricare_advisor.repository.CategoryDiseaseRuleRepository;
//import com.nutricare.nutricare_advisor.repository.DiseaseRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/category-rules")
@RequiredArgsConstructor 
@PreAuthorize("hasRole('ADMIN')")
public class AdminCategoryRuleController {

    private final CategoryDiseaseRuleRepository repo;
//    private final DiseaseRepository diseaseRepo;

    @PostMapping
    public CategoryDiseaseRule  add(@RequestBody CategoryDiseaseRule rule) {
        return repo.save(rule);
    }

    @GetMapping
    public List<CategoryDiseaseRule> list() {
        return repo.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

