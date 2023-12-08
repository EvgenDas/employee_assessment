package com.dashenckoevgeny.spring.springboot.employee_assessment.service;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import java.util.List;

public interface EmployeeService {

  Employee getById(Integer id);

  Employee getByLogin(String login);

  Employee update(Employee employee);

  Employee create(Employee employee);

  boolean isAssessmentOwner(Integer employeeId, Integer assessmentId);

  boolean isEmployeesManager(Integer employeeId, Integer managerId);

  boolean isEmployeesExpert(Integer employeeId, Integer expertId);

  List<Employee> getAllEmployeesByManager(Integer id);

  List<Employee> getAllEmployeesByExpert(Integer id);

  void delete(Integer id);

}
