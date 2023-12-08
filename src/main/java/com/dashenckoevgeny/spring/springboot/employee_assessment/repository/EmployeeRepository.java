package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface EmployeeRepository {
  Optional<Employee> findByLogin(String login);

  Optional<Employee> findById(Integer id);

  void update(Employee employee);

  void create(Employee employee);

  void insertEmployeeRole(@Param("employeeId") Integer employeeId, @Param("role") Role role);

  boolean isAssessmentOwner(@Param("employeeId") Integer employeeId, @Param("assessmentId") Integer assessmentId);

  boolean isEmployeesManager(@Param("employeeId") Integer employeeId, @Param("managerId") Integer managerId);

  boolean isEmployeesExpert(@Param("employeeId") Integer employeeId, @Param("expertId") Integer expertId);

  List<Employee> findAllEmployeesByManager(Integer id);

  List<Employee> findAllEmployeesByExpert(Integer id);


  void delete(Integer id);
}

