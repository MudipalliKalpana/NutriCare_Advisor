package com.nutricare.nutricare_advisor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diseases")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disease {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long diseaseId;
	
	@NotBlank(message = "Disease name cannot be empty")
	private String diseaseName;
	
	@NotBlank(message = "Disease description is required")
	private String description;
	private Boolean isActive=true;
	
}