package com.nutricare.nutricare_advisor.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.entity.AdminAuditLog;
import com.nutricare.nutricare_advisor.repository.AdminAuditLogRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/audit-logs")
@RequiredArgsConstructor
public class AdminAuditLogController {

    private final AdminAuditLogRepository auditLogRepository;

    @GetMapping
    public List<AdminAuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll(
            Sort.by(Sort.Direction.DESC, "timestamp")
        );
    }
}
