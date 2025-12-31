package com.nutricare.nutricare_advisor.service;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.nutricare.nutricare_advisor.entity.Disease;
import com.nutricare.nutricare_advisor.exception.ResourceNotFoundException;
import com.nutricare.nutricare_advisor.repository.DiseaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminDiseaseService {

	private final DiseaseRepository diseaseRepository;

	public Disease addDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}

	public List<Disease> getAllDiseases() {
		return diseaseRepository.findAll();
	}

	public Disease updateDisease(Long id, Disease updated) {
		Disease disease = diseaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Disease not found with id " + id));
		disease.setDiseaseName(updated.getDiseaseName());
		disease.setDescription(updated.getDescription());
		disease.setIsActive(updated.getIsActive());

		return diseaseRepository.save(disease);
	}

	public void deleteDisease(Long id) {
		Disease disease = diseaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Disease not found with id " + id));
		disease.setIsActive(false);
		diseaseRepository.save(disease);
	}

	public Page<Disease> searchDiseases(String keyword, Pageable pageable) {
		if (keyword == null || keyword.isBlank()) {
			return diseaseRepository.findAll(pageable);
		}

		return diseaseRepository.findByDiseaseNameContainingIgnoreCase(keyword, pageable);
	}
}
