package com.nutricare.nutricare_advisor.controller;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.entity.Disease;
import com.nutricare.nutricare_advisor.repository.DiseaseRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/diseases")
@RequiredArgsConstructor
public class DiseaseController {

	private final DiseaseRepository diseaseRepo;
	
	@GetMapping("/search")
	public Page<Disease> search(
	  @RequestParam(required = false, defaultValue = "") String keyword,
	  Pageable pageable
	) {
	  if (keyword.isBlank()) {
	    return diseaseRepo.findAll(pageable);
	  }
	  return diseaseRepo.findByDiseaseNameContainingIgnoreCase(keyword, pageable);
	}

}
