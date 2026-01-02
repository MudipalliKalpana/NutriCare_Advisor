package com.nutricare.nutricare_advisor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.nutricare.nutricare_advisor.dto.RuleRequestDto;
import com.nutricare.nutricare_advisor.entity.FoodDiseaseRule;
import com.nutricare.nutricare_advisor.service.AdminAuditService;
import com.nutricare.nutricare_advisor.service.AdminRuleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/rules")
@RequiredArgsConstructor
public class AdminRuleController {

    private final AdminRuleService service;
    private final AdminAuditService auditService;

    // =========================
    // ADD RULE
    // =========================
    @PostMapping
    public FoodDiseaseRule addRule(
            @RequestBody RuleRequestDto dto,
            Authentication auth
    ) {

        FoodDiseaseRule rule = service.addRule(
                dto.getFoodId(),
                dto.getDiseaseId(),
                dto.getImpact(),
                dto.getExplanation()
        );

        auditService.log(
                auth.getName(),
                "ADD_RULE",
                "RULE",
                rule.getRuleId()
        );

        return rule;
    }

    // =========================
    // LIST RULES
    // =========================
    @GetMapping
    public List<FoodDiseaseRule> listRules() {
        return service.getAllRules();
    }

    // =========================
    // UPDATE RULE
    // =========================
    @PutMapping("/{ruleId}")
    public FoodDiseaseRule updateRule(
            @PathVariable Long ruleId,
            @RequestBody RuleRequestDto dto,
            Authentication auth
    ) {

        FoodDiseaseRule updated = service.updateRule(
                ruleId,
                dto.getFoodId(),
                dto.getDiseaseId(),
                dto.getImpact(),
                dto.getExplanation()
        );

        auditService.log(
                auth.getName(),
                "UPDATE_RULE",
                "RULE",
                ruleId
        );

        return updated;
    }

    // =========================
    // DELETE RULE
    // =========================
    @DeleteMapping("/{ruleId}")
    public ResponseEntity<Void> deleteRule(
            @PathVariable Long ruleId,
            Authentication auth
    ) {

        service.deleteRule(ruleId);

        auditService.log(
                auth.getName(),
                "DELETE_RULE",
                "RULE",
                ruleId
        );

        return ResponseEntity.noContent().build();
    }
}
