package com.nutricare.nutricare_advisor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "food_disease_rules", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "food_id", "disease_id" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDiseaseRule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ruleId;

	@ManyToOne
	@JoinColumn(name = "food_id")
	private Food food;

	@ManyToOne
	@JoinColumn(name = "disease_id")
	private Disease disease;

	@Enumerated(EnumType.STRING)
	private Impact impact;

	private String explanation;
	
	private Boolean isActive = true;

}
