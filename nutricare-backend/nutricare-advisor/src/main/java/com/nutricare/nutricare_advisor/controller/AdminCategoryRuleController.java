package com.nutricare.nutricare_advisor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.nutricare.nutricare_advisor.entity.CategoryDiseaseRule;
import com.nutricare.nutricare_advisor.repository.CategoryDiseaseRuleRepository;
import com.nutricare.nutricare_advisor.service.AdminAuditService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/category-rules")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminCategoryRuleController {

    private final CategoryDiseaseRuleRepository repo;
    private final AdminAuditService auditService;

    // =========================
    // ADD CATEGORY RULE
    // =========================
    @PostMapping
    public CategoryDiseaseRule add(
            @RequestBody CategoryDiseaseRule rule,
            Authentication auth
    ) {

        CategoryDiseaseRule saved = repo.save(rule);

        auditService.log(
                auth.getName(),
                "ADD_CATEGORY_RULE",
                "CATEGORY_RULE",
                saved.getId()
        );

        return saved;
    }

    // =========================
    // LIST CATEGORY RULES
    // =========================
    @GetMapping
    public List<CategoryDiseaseRule> list() {
        return repo.findAll();
    }

    // =========================
    // DELETE CATEGORY RULE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            Authentication auth
    ) {

        repo.deleteById(id);

        auditService.log(
                auth.getName(),
                "DELETE_CATEGORY_RULE",
                "CATEGORY_RULE",
                id
        );

        return ResponseEntity.noContent().build();
    }
}
