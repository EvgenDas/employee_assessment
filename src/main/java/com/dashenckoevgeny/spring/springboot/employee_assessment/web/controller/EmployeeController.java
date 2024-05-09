package com.dashenckoevgeny.spring.springboot.employee_assessment.web.controller;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeAssessmentService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.EmployeeAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.employee.EmployeeDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnCreate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers.EmployeeAssessmentMapper;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Validated
@Tag(name = "Employee Controller", description = "Employee API")
public class EmployeeController {

  private final EmployeeService employeeService;
  private final EmployeeAssessmentService assessmentService;

  private final EmployeeMapper employeeMapper;
  private final EmployeeAssessmentMapper assessmentMapper;

  @GetMapping("/{id}")
  @Operation(summary = "Get EmployeeDto by id")
  @PreAuthorize("@customSecurityExpression.canAccessEmployee(#id)")
  public EmployeeDto getById(@PathVariable Integer id) {
    Employee employee = employeeService.getById(id);
    return employeeMapper.toDto(employee);
  }


  @GetMapping("/{id}/assessments")
  @Operation(summary = "Get all Employee assessments")
  @PreAuthorize("@customSecurityExpression.canAccessEmployee(#id)")
  public List<EmployeeAssessmentDto> getAssessmentsByUserId(@PathVariable Integer id) {
    List<EmployeeAssessment> assessments = assessmentService.getAllByEmployeeId(id);
    return assessmentMapper.toDto(assessments);
  }

  @GetMapping("/{id}/staff")
  @Operation(summary = "Get all Employees by manager")
  @PreAuthorize("@customSecurityExpression.canAccessEmployeeOnlyForManagerAndExpert(#id)")
  public List<Employee> getEmployeesByManagerId(@PathVariable Integer id) {
    List<Employee> employees = employeeService.getAllEmployeesByManager(id);
    return employeeMapper.toDto(employees);
  }

  @GetMapping("/{id}/staff/expertise")
  @Operation(summary = "Get all Employees by expert")
  @PreAuthorize("@customSecurityExpression.canAccessEmployeeOnlyForManagerAndExpert(#id)")
  public List<Employee> getEmployeesByExpertId(@PathVariable Integer id) {
    List<Employee> employees = employeeService.getAllEmployeesByExpert(id);
    return employeeMapper.toDto(employees);
  }

  @PutMapping
  @Operation(summary = "Update employee")
  @PreAuthorize("@customSecurityExpression.canAccessEmployee(#dto.id)")
  public EmployeeDto update(@Validated(OnUpdate.class) @RequestBody EmployeeDto dto) {
    Employee employee = employeeMapper.toEntity(dto);
    Employee updatedEmployee = employeeService.update(employee);
    return employeeMapper.toDto(updatedEmployee);
  }

  @PostMapping(value = "/{id}/assessments")
  @Operation(summary = "Add assessment to employee")
  @PreAuthorize("@customSecurityExpression.createOnlyForAdmin()")
  public EmployeeAssessmentDto createAssessment(@PathVariable Integer id,
      @Validated(OnCreate.class) @RequestBody EmployeeAssessmentDto dto) {
    EmployeeAssessment assessment = assessmentMapper.toEntity(dto);
    EmployeeAssessment createdAssessment = assessmentService.create(id, assessment);
    return assessmentMapper.toDto(createdAssessment);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete Employee by id")
  @PreAuthorize("@customSecurityExpression.createOnlyForAdmin()")
  public void deleteById(@PathVariable Integer id) {
    employeeService.delete(id);
  }
}
