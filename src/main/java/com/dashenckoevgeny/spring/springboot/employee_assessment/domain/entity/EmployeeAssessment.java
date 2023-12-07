package com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EmployeeAssessment implements Serializable {

  private int id;

  private int ownAssessment;

  private int expertAssessment;

  private int managerAssessment;

  private int finalAssessment;

  private LocalDateTime dateOfAssessment;

  private boolean isActive;

}
