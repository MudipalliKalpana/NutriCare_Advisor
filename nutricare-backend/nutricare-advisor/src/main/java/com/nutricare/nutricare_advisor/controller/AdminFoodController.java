package com.nutricare.nutricare_advisor.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.entity.Food;
import com.nutricare.nutricare_advisor.service.AdminFoodService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/foods")
@RequiredArgsConstructor
public class AdminFoodController {

	private final AdminFoodService adminFoodService;
	
	@PostMapping
	public Food add(@Valid @RequestBody Food food) {
		return adminFoodService.addFood(food);
	}
	
	@GetMapping
	public List<Food> list(){
		return adminFoodService.getAllFoods();
	}
	
	@PutMapping("/{id}")
	public Food update(@PathVariable Long id, @Valid @RequestBody Food food) {
		return adminFoodService.updateFood(id, food);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		adminFoodService.deleteFood(id);
		return "Food deleted!";
	}
	
	@GetMapping("/search")
	public Page<Food> searchFoods(@RequestParam(required = false) String keyword,
								 @RequestParam(defaultValue = "0") int page,
								 @RequestParam(defaultValue = "5") int size){
		Pageable pageable=PageRequest.of(page, size);
		return adminFoodService.searchFoods(keyword, pageable);
	}
}
