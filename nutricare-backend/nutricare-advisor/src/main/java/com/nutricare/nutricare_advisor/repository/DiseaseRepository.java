package com.nutricare.nutricare_advisor.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nutricare.nutricare_advisor.entity.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long>{

	Page<Disease> findByDiseaseNameContainingIgnoreCase(String keyword, Pageable pageable);
	Page<Disease> findByIsActiveTrue(Pageable pageable);

}