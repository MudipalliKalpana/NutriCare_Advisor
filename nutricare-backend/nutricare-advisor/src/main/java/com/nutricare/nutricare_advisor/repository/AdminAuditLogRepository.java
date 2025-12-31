package com.nutricare.nutricare_advisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricare.nutricare_advisor.entity.AdminAuditLog;

public interface AdminAuditLogRepository extends JpaRepository<AdminAuditLog, Long> {


}
