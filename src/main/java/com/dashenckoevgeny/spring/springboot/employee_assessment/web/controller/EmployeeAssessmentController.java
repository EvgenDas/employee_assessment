package com.dashenckoevgeny.spring.springboot.employee_assessment.web.controller;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeAssessmentService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.EmployeeAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ExpertAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ManagerAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.OwnAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers.EmployeeAssessmentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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


  @GetMapping("/{id}")
  @Operation(summary = "Get EmployeeAssessmentDto by id")
  @PreAuthorize("canAccessAssessment(#id)")
  public EmployeeAssessmentDto getById(@PathVariable Integer id) {
    EmployeeAssessment assessment = assessmentService.getById(id);
    return assessmentMapper.toDto(assessment);
  }

  @PatchMapping("/own/{id}")
  @Operation(summary = "Update own assessment")
  @PreAuthorize("@customSecurityExpression.canAccessOwnAssessment(#dto.id)")
  public OwnAssessmentDto updateOwnAssessment(@PathVariable Integer id,
      @RequestBody OwnAssessmentDto dto) {
    return assessmentService.updateOwnAssessment(dto);
  }

  @PatchMapping("/manager/{id}")
  @Operation(summary = "Update manager assessment")
  @PreAuthorize("@customSecurityExpression.canAccessManagerAssessment(#dto.id)")
  public ManagerAssessmentDto updateManagerAssessment(@PathVariable Integer id,
      @RequestBody ManagerAssessmentDto dto) {
    return assessmentService.updateManagerAssessment(dto);
  }

  @PatchMapping("/expert/{id}")
  @Operation(summary = "Update expert assessment")
  @PreAuthorize("@customSecurityExpression.canAccessExpertAssessment(#dto.id)")
  public ExpertAssessmentDto updateExpertAssessment(@PathVariable Integer id,
      @RequestBody ExpertAssessmentDto dto) {
    return assessmentService.updateExpertAssessment(dto);
  }

  @PutMapping
  @Operation(summary = "Update EmployeeAssessmentDto")
  @PreAuthorize("@customSecurityExpression.createOnlyForAdmin()")
  public EmployeeAssessmentDto update(
      @Validated(OnUpdate.class) @RequestBody EmployeeAssessmentDto dto) {
    EmployeeAssessment assessment = assessmentMapper.toEntity(dto);
    EmployeeAssessment updatedAssessment = assessmentService.update(assessment);
    return assessmentMapper.toDto(updatedAssessment);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete assessment by id")
  @PreAuthorize("@customSecurityExpression.createOnlyForAdmin()")
  public void deleteById(@PathVariable Integer id) {
    assessmentService.delete(id);
  }
}
