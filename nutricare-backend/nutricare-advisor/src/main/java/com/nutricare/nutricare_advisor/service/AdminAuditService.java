package com.nutricare.nutricare_advisor.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nutricare.nutricare_advisor.entity.AdminAuditLog;
import com.nutricare.nutricare_advisor.repository.AdminAuditLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuditService {

    private final AdminAuditLogRepository repo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String adminEmail, String action, String entityType, Long entityId) {
        try {
            AdminAuditLog log = new AdminAuditLog();
            log.setAdminEmail(adminEmail);
            log.setAction(action);
            log.setEntityType(entityType);
            log.setEntityId(entityId);
            log.setTimestamp(LocalDateTime.now());

            repo.save(log);
        } catch (Exception e) {
            // IMPORTANT: audit must NEVER break business logic
            System.err.println("⚠️ Admin audit failed: " + e.getMessage());
        }
    }
}
