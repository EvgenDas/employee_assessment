package com.dashenckoevgeny.spring.springboot.employee_assessment.service.Impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Override
  public Employee getById(Integer id) {
    return null;
  }

  @Override
  public Employee getByLogin(String login) {
    return null;
  }

  @Override
  public Employee update(Employee employee) {
    return null;
  }

  @Override
  public Employee create(Employee employee) {
    return null;
  }

  @Override
  public boolean isAssessmentOwner(Integer employeeId, Integer assessmentId) {
    return false;
  }

  @Override
  public void delete(Integer id) {

  }
}
