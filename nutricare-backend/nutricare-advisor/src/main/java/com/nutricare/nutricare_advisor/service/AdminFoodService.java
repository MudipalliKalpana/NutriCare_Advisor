package com.nutricare.nutricare_advisor.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nutricare.nutricare_advisor.entity.Food;
import com.nutricare.nutricare_advisor.exception.ResourceNotFoundException;
import com.nutricare.nutricare_advisor.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminFoodService {

	private final FoodRepository foodRepository;
	
	public Food addFood(Food food) {
		return foodRepository.save(food);
	}
	
	public List<Food> getAllFoods(){
		return foodRepository.findAll();
	}
	
	public Food updateFood(Long id, Food updated) {
		Food food=foodRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Food not found!"));
		food.setFoodName(updated.getFoodName());
		food.setCategory(updated.getCategory());
		food.setPortionSize(updated.getPortionSize());
		food.setIsActive(updated.getIsActive());
		
		return foodRepository.save(food);
	}
	
	public void deleteFood(Long id) {
		foodRepository.deleteById(id);
	}
	
	public Page<Food> searchFoods(String keyword, Pageable pageable){
		if(keyword==null || keyword.isBlank()) {
			return foodRepository.findAll(pageable);
		}
		
		return foodRepository.findByFoodNameContainingIgnoreCase(keyword, pageable);
	}
}
