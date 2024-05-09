package com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

@Data
public class EmployeeAssessment implements Serializable {

  private int id;

  private String ownAssessment;

  private String expertAssessment;

  private String managerAssessment;

  private String finalAssessment;

  private LocalDateTime dateOfAssessment;

  private boolean isActive;

}
