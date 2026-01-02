package com.nutricare.nutricare_advisor.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutricare.nutricare_advisor.entity.Disease;
import com.nutricare.nutricare_advisor.service.AdminDiseaseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/diseases")
@RequiredArgsConstructor
public class AdminDiseaseController {
	
	private final AdminDiseaseService adminDiseaseService;

	
	@PostMapping
	public Disease add(@Valid @RequestBody Disease disease) {
		return adminDiseaseService.addDisease(disease);
	}
	
	@GetMapping
	public List<Disease> list(){
		return adminDiseaseService.getAllDiseases();
	}
	
	@PutMapping("/{id}")
	public Disease update(@PathVariable Long id, @Valid @RequestBody Disease disease) {
		return adminDiseaseService.updateDisease(id, disease);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    adminDiseaseService.deleteDisease(id);
	    return ResponseEntity.noContent().build(); // ✅ 204 No Content
	}

	
	@GetMapping("/search")
	public Page<Disease> searchDiseases(
	        @RequestParam(required = false) String keyword,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    Pageable pageable = PageRequest.of(page, size);
	    return adminDiseaseService.searchDiseases(keyword, pageable);
	}

}
