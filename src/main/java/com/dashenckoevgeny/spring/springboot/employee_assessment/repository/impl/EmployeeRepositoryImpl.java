package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

  @Override
  public Optional<Employee> findByLogin(String login) {
    return Optional.empty();
  }

  @Override
  public Optional<Employee> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public void update(Employee employee) {

  }

  @Override
  public void create(Employee employee) {

  }

  @Override
  public void insertEmployeeRole(Integer employeeId, Role role) {

  }

  @Override
  public boolean isAssessmentOwner(Integer employeeId, Integer assessmentId) {
    return false;
  }

  @Override
  public void delete(Integer id) {

  }
}
