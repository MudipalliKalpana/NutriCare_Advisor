package com.nutricare.nutricare_advisor.mongo;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "food_nutrition")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodNutrition {

	@Id
	private String id;
	
	private String foodName;
	
	private Map<String, Object> nutrients;
	
	private List<String> benefits;
	
	private List<String> warningStrings;
	
}
