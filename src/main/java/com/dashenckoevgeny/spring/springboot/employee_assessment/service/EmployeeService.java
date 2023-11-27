package com.dashenckoevgeny.spring.springboot.employee_assessment.service;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  Employee getById(Integer id);

  Employee getByLogin(String login);

  Employee update(Employee employee);

  Employee create(Employee employee);

  boolean isAssessmentOwner(Integer employeeId, Integer assessmentId);

  void delete(Integer id);

}
