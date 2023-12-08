package com.dashenckoevgeny.spring.springboot.employee_assessment.service.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceNotFoundException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeRepository;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
  @Cacheable(value = "EmployeeService::getById", key = "#id")
  public Employee getById(Integer id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "EmployeeService::getByLogin", key = "#login")
  public Employee getByLogin(String login) {
    return employeeRepository.findByLogin(login)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
  }

  @Override
  @Transactional
  @Caching(put = {
      @CachePut(value = "EmployeeService::getById", key = "#employee.id"),
      @CachePut(value = "EmployeeService::getByLogin", key = "#employee.login")
  })
  public Employee update(Employee employee) {
    employee.setPassword(passwordEncoder.encode(employee.getPassword()));
    employeeRepository.update(employee);
    return employee;
  }

  @Override
  @Transactional
  @Caching(cacheable = {
      @Cacheable(value = "EmployeeService::getById", key = "#employee.id"),
      @Cacheable(value = "EmployeeService::getByLogin", key = "#employee.login")
  })
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
  @Cacheable(value = "EmployeeService::isAssessmentOwner", key = "#employeeId + '.' + #assessmentId")
  public boolean isAssessmentOwner(Integer employeeId, Integer assessmentId) {
    return employeeRepository.isAssessmentOwner(employeeId, assessmentId);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isEmployeesManager(Integer employeeId, Integer managerId) {
    return employeeRepository.isEmployeesManager(employeeId, managerId);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isEmployeesExpert(Integer employeeId, Integer expertId) {
    return employeeRepository.isEmployeesExpert(employeeId, expertId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Employee> getAllEmployeesByManager(Integer id) {
    return employeeRepository.findAllEmployeesByManager(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Employee> getAllEmployeesByExpert(Integer id) {
    return employeeRepository.findAllEmployeesByExpert(id);
  }

  @Override
  @Transactional
  @CacheEvict(value = "EmployeeService::getById", key = "#id")
  public void delete(Integer id) {
    employeeRepository.delete(id);
  }
}
