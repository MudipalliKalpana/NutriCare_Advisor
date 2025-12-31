package com.nutricare.nutricare_advisor.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.dto.RecommendationDTO;
import com.nutricare.nutricare_advisor.entity.Disease;
import com.nutricare.nutricare_advisor.service.RecommendationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecommendationController {

	private final RecommendationService service;
	
	@PostMapping("/recommend")
	public ResponseEntity<List<RecommendationDTO>> recommend(
	        @RequestBody List<Long> diseaseIds
	) {
		
	    if (diseaseIds == null || diseaseIds.isEmpty()) {
	        return ResponseEntity.badRequest().body(
	            List.of(
	                new RecommendationDTO(
	                    "Water",
	                    "SAFE",
	                    "No diseases selected",
	                    false
	                )
	            )
	        );
	    }

	    return ResponseEntity.ok(
	        service.recommendFoods(diseaseIds)
	    );
	}

	
	@GetMapping("/listAll")
	public List<Disease> listDiseases(){
		return service.listDiseases();
	}
}
