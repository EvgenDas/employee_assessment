package com.dashenckoevgeny.spring.springboot.employee_assessment.service.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceNotFoundException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeAssessmentRepository;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeAssessmentService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ExpertAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ManagerAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.OwnAssessmentDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeAssessmentServiceImpl implements EmployeeAssessmentService {

  private final EmployeeAssessmentRepository employeeAssessmentRepository;

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "EmployeeAssessmentService::getById", key = "#id")
  public EmployeeAssessment getById(Integer id) {
    return employeeAssessmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Assessment not found."));
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "EmployeeAssessmentService::getOwnAssessmentById", key = "#id")
  public String getOwnAssessmentById(Integer id) {
    return employeeAssessmentRepository.findOwnAssessmentById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Assessment not found."));
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "EmployeeAssessmentService::getManagerAssessmentById", key = "#id")
  public Map<String, Integer> getManagerAssessmentById(Integer id) {
    return employeeAssessmentRepository.findManagerAssessmentById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Assessment not found."));
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "EmployeeAssessmentService::getExpertAssessmentById", key = "#id")
  public Map<String, Integer> getExpertAssessmentById(Integer id) {
    return employeeAssessmentRepository.findExpertAssessmentById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Assessment not found."));
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "EmployeeAssessmentService::getFinalAssessmentById", key = "#id")
  public Map<String, Integer> getFinalAssessmentById(Integer id) {
    return employeeAssessmentRepository.findFinalAssessmentById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Assessment not found."));
  }

  @Override
  @Transactional(readOnly = true)
  public List<EmployeeAssessment> getAllByEmployeeId(Integer id) {
    return employeeAssessmentRepository.findAllByEmployeeId(id);
  }

  @Override
  @Transactional
  @CachePut(value = "EmployeeAssessmentService::getById", key = "#assessment.id")
  public EmployeeAssessment update(EmployeeAssessment assessment) {
    employeeAssessmentRepository.update(assessment);
    return assessment;
  }


  @Override
  @Transactional
  @Cacheable(value = "EmployeeAssessmentService::getById", key = "#assessment.id")
  public EmployeeAssessment create(Integer employeeId, EmployeeAssessment assessment) {
    assessment.setActive(true);
    employeeAssessmentRepository.create(assessment);
    employeeAssessmentRepository.assignToEmployeeById(employeeId, assessment.getId());
    return assessment;
  }

  @Override
  @Transactional
  @CachePut(value = "EmployeeAssessmentService::updateOwnAssessment", key = "#assessment.id")
  public OwnAssessmentDto updateOwnAssessment(OwnAssessmentDto assessment) {
    employeeAssessmentRepository.updateOwnAssessment(assessment);
    return assessment;
  }

  @Override
  @Transactional
  @CachePut(value = "EmployeeAssessmentService::updateManagerAssessment", key = "#assessment.id")
  public ManagerAssessmentDto updateManagerAssessment(ManagerAssessmentDto assessment) {
    employeeAssessmentRepository.updateManagerAssessment(assessment);
    return assessment;
  }

  @Override
  @Transactional
  @CachePut(value = "EmployeeAssessmentService::updateExpertAssessment", key = "#assessment.id")
  public ExpertAssessmentDto updateExpertAssessment(ExpertAssessmentDto assessment) {
    employeeAssessmentRepository.updateExpertAssessment(assessment);
    return assessment;
  }

  @Override
  @Transactional
  @CacheEvict(value = "EmployeeAssessmentService::getById", key = "#id")
  public void delete(Integer id) {
    employeeAssessmentRepository.delete(id);
  }
}
