package com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment;

import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnCreate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
public class EmployeeAssessmentDto {

  @NotNull(message = "Id must be not null", groups = OnUpdate.class)
  private Integer id;

//  @NotNull(message = "Own assessment must be not null", groups = {OnUpdate.class, OnCreate.class})
  private String ownAssessment;

//  @NotNull(message = "Expert assessment must be not null", groups = {OnUpdate.class, OnCreate.class})
  private String expertAssessment;

//  @NotNull(message = "Manager assessment must be not null", groups = {OnUpdate.class, OnCreate.class})
  private String managerAssessment;

//  @NotNull(message = "Final assessment must be not null", groups = {OnUpdate.class, OnCreate.class})
  private String finalAssessment;

  @DateTimeFormat(iso = ISO.TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateOfAssessment;

  private boolean isActive;

}
