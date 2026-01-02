package com.nutricare.nutricare_advisor.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodNutritionRepository extends MongoRepository<FoodNutrition, String> {

	Optional<FoodNutrition> findByFoodNameIgnoreCase(String foodName);
}
