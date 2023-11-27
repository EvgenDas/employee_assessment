package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {
  Optional<Employee> findByLogin(String login);

  Optional<Employee> findById(Integer id);

  void update(Employee employee);

  void create(Employee employee);

  void insertEmployeeRole(Integer employeeId, Role role);

  boolean isAssessmentOwner(Integer employeeId, Integer assessmentId);

  void delete(Integer id);
}

