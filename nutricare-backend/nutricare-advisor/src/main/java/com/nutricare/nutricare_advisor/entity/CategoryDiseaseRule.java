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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category_disease_rules")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDiseaseRule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String foodCategory;

    @ManyToOne 
    @JoinColumn (name = "disease_id")
    private Disease disease;

    @Enumerated(EnumType.STRING)
    private Impact impact;

    private String explanation;
}
