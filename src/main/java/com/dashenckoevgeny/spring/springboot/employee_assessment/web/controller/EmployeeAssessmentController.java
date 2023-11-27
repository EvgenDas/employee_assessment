package com.dashenckoevgeny.spring.springboot.employee_assessment.web.controller;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeAssessmentService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.EmployeeAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers.EmployeeAssessmentMapper;
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
@RequestMapping("api/vi/assessments")
@RequiredArgsConstructor
@Validated
public class EmployeeAssessmentController {

  private final EmployeeAssessmentService assessmentService;

  private final EmployeeAssessmentMapper assessmentMapper;

  @PutMapping
  public EmployeeAssessmentDto update(@Validated(OnUpdate.class) @RequestBody EmployeeAssessmentDto dto) {
    EmployeeAssessment assessment = assessmentMapper.toEntity(dto);
    EmployeeAssessment updatedAssessment = assessmentService.update(assessment);
    return assessmentMapper.toDto(updatedAssessment);
  }

  @GetMapping("/{id}")
  public EmployeeAssessmentDto getById(@PathVariable Integer id) {
    EmployeeAssessment assessment = assessmentService.getById(id);
    return assessmentMapper.toDto(assessment);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Integer id) {
    assessmentService.delete(id);
  }

}
