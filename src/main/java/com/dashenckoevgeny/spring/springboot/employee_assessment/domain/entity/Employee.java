package com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

  private int id;

  private String name;

  private String surname;

  private int managerId;

  private int expertId;

  private Set<Role> roles;

  private List<EmployeeAssessment> employeeAssessments;

  private LocalDateTime dateOfNextAssessment;

  private String login;

  private String password;
}
