package com.nutricare.nutricare_advisor.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin_audit_logs")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminAuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String adminEmail;
	private String action; // ADD_RULE, DELETE_DISEASE etc
	private String entityType; // FOOD, DISEASE, RULE
	private Long entityId;

	private LocalDateTime timestamp;
}
