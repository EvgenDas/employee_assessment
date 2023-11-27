package com.dashenckoevgeny.spring.springboot.employee_assessment.service.Impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeAssessmentService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAssessmentServiceImpl implements EmployeeAssessmentService {

  @Override
  public EmployeeAssessment getById(Integer id) {
    return null;
  }

  @Override
  public List<EmployeeAssessment> getAllByEmployeeId(Integer id) {
    return null;
  }

  @Override
  public EmployeeAssessment update(EmployeeAssessment assessment) {
    return null;
  }

  @Override
  public EmployeeAssessment create(EmployeeAssessment assessment, Integer id) {
    return null;
  }

  @Override
  public EmployeeAssessment delete(Integer id) {
    return null;
  }
}
