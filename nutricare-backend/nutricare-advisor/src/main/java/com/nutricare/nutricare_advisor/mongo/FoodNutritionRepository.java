package com.nutricare.nutricare_advisor.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodNutritionRepository extends MongoRepository<FoodNutrition, String> {

}
