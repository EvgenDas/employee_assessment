package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Mapper
public interface EmployeeRepository {
  Optional<Employee> findByLogin(String login);

  Optional<Employee> findById(Integer id);

  void update(Employee employee);

  void create(Employee employee);

  void insertEmployeeRole(@Param("employeeId") Integer employeeId, @Param("role") Role role);

  boolean isAssessmentOwner(@Param("employeeId") Integer employeeId, @Param("assessmentId") Integer assessmentId);

  void delete(Integer id);
}

