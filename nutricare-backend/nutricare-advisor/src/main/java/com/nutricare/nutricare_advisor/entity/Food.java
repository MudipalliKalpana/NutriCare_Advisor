package com.nutricare.nutricare_advisor.entity;

import jakarta.persistence.Column;
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
@Table(name = "foods")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodId;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "Food name is mandatory")
	private String foodName;
	
	@NotBlank(message = "Category is mandatory")
	private String category;
	
	@NotBlank(message = "Portion size is mandatory")
	private String portionSize;
	
	private Boolean isActive=true;
}
