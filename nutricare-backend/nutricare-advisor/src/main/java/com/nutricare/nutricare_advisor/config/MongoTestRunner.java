//package com.nutricare.nutricare_advisor.config;
//
//import org.springframework.stereotype.Component;
//
//import com.nutricare.nutricare_advisor.mongo.FoodNutritionRepository;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//public class MongoTestRunner {
//
//	private final FoodNutritionRepository repo;
//	
//	@PostConstruct
//	public void testMongo() {
//		System.out.println("MongoDB test data:");
//		repo.findAll().forEach(System.out::println);
//	}
//}
