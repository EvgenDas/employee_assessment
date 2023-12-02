package com.dashenckoevgeny.spring.springboot.employee_assessment.service.Impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceNotFoundException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeRepository;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional(readOnly = true)
  public Employee getById(Integer id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
  }

  @Override
  @Transactional(readOnly = true)
  public Employee getByLogin(String login) {
    return employeeRepository.findByLogin(login)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
  }

  @Override
  @Transactional
  public Employee update(Employee employee) {
    employee.setPassword(passwordEncoder.encode(employee.getPassword()));
    employeeRepository.update(employee);
    return employee;
  }

  @Override
  @Transactional
  public Employee create(Employee employee) {
    if(employeeRepository.findByLogin(employee.getLogin()).isPresent()) {
      throw new IllegalStateException("Employee already exists.");
    }
    employee.setPassword(passwordEncoder.encode(employee.getPassword()));
    employeeRepository.create(employee);
    Set<Role> roles = Set.of(Role.ROLE_USER);
    employeeRepository.insertEmployeeRole(employee.getId(), Role.ROLE_USER);
    employee.setRoles(roles);
    return employee;
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isAssessmentOwner(Integer employeeId, Integer assessmentId) {
    return employeeRepository.isAssessmentOwner(employeeId, assessmentId);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    employeeRepository.delete(id);
  }
}
