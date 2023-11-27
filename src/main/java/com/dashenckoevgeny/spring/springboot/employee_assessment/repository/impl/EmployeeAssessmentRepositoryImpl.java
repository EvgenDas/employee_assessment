package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeAssessmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeAssessmentRepositoryImpl implements EmployeeAssessmentRepository {

  @Override
  public Optional<EmployeeAssessment> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public List<EmployeeAssessment> findAllByEmployeeId(Integer id) {
    return null;
  }

  @Override
  public void assignToEmployeeById(Integer assessmentId, Integer employeeId) {

  }

  @Override
  public void update(EmployeeAssessment assessment) {

  }

  @Override
  public void create(EmployeeAssessment assessment) {

  }

  @Override
  public void delete(Integer id) {

  }
}
