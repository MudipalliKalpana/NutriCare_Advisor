package com.nutricare.nutricare_advisor.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricare.nutricare_advisor.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

	Page<Food> findByFoodNameContainingIgnoreCase(String keyword, Pageable pageable);
}