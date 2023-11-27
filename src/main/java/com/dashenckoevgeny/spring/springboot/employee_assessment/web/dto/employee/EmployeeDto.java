package com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.employee;

import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnCreate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
public class EmployeeDto {

  @NotNull(message = "Id must be not null", groups = OnUpdate.class)
  private Integer id;

  @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
  @Length(max = 255, message = "Name length must be smaller than 255 symbols",
      groups = {OnUpdate.class, OnCreate.class})
  private String name;

  @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
  @Length(max = 255, message = "Name length must be smaller than 255 symbols",
      groups = {OnUpdate.class, OnCreate.class})
  private String surname;

  @NotNull(message = "managerId must be not null", groups = OnUpdate.class)
  private int managerId;

  @NotNull(message = "expertId must be not null", groups = OnUpdate.class)
  private int expertId;

  @DateTimeFormat(iso = ISO.TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateOfNextAssessment;

  @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
  @Length(max = 255, message = "Name length must be smaller than 255 symbols",
      groups = {OnUpdate.class, OnCreate.class})
  private String login;

  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class})
  private String password;

}
