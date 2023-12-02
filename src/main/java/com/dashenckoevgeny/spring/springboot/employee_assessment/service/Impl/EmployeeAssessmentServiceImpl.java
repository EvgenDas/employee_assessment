package com.dashenckoevgeny.spring.springboot.employee_assessment.service.Impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceNotFoundException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeAssessmentRepository;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeAssessmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeAssessmentServiceImpl implements EmployeeAssessmentService {

  private final EmployeeAssessmentRepository employeeAssessmentRepository;

  @Override
  @Transactional(readOnly = true)
  public EmployeeAssessment getById(Integer id) {
    return employeeAssessmentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Assessment not found."));
  }

  @Override
  @Transactional(readOnly = true)
  public List<EmployeeAssessment> getAllByEmployeeId(Integer id) {
    return employeeAssessmentRepository.findAllByEmployeeId(id);
  }

  @Override
  @Transactional
  public EmployeeAssessment update(EmployeeAssessment assessment) {
    employeeAssessmentRepository.update(assessment);
    return assessment;
  }

  @Override
  @Transactional
  public EmployeeAssessment create(Integer employeeId, EmployeeAssessment assessment) {
    assessment.setActive(true);
    employeeAssessmentRepository.create(assessment);
    employeeAssessmentRepository.assignToEmployeeById(employeeId, assessment.getId());
    return assessment;
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    employeeAssessmentRepository.delete(id);
  }
}
