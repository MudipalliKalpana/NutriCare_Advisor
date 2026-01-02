package com.nutricare.nutricare_advisor.dto;

import com.nutricare.nutricare_advisor.entity.Impact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleRequestDto {

    private Long foodId;
    private Long diseaseId;
    private Impact impact;
    private String explanation;
}
