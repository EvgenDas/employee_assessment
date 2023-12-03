package com.dashenckoevgeny.spring.springboot.employee_assessment.web.controller;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeAssessmentService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.EmployeeAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers.EmployeeAssessmentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/assessments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Assessment Controller", description = "Assessment API")
public class EmployeeAssessmentController {

  private final EmployeeAssessmentService assessmentService;

  private final EmployeeAssessmentMapper assessmentMapper;

  @PutMapping
  @Operation(summary = "Update EmployeeAssessmentDto")
  public EmployeeAssessmentDto update(@Validated(OnUpdate.class) @RequestBody EmployeeAssessmentDto dto) {
    EmployeeAssessment assessment = assessmentMapper.toEntity(dto);
    EmployeeAssessment updatedAssessment = assessmentService.update(assessment);
    return assessmentMapper.toDto(updatedAssessment);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get EmployeeAssessmentDto by id")
  public EmployeeAssessmentDto getById(@PathVariable Integer id) {
    EmployeeAssessment assessment = assessmentService.getById(id);
    return assessmentMapper.toDto(assessment);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete assessment by id")
  public void deleteById(@PathVariable Integer id) {
    assessmentService.delete(id);
  }
}
