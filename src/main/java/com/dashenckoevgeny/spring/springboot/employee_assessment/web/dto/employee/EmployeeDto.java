package com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.employee;

import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnCreate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@Schema(description = "Employee Dto")
public class EmployeeDto {

  @Schema(description = "Employee id", example = "1")
  @NotNull(message = "Id must be not null", groups = OnUpdate.class)
  private Integer id;

  @Schema(description = "Employee name", example = "John")
  @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
  @Length(max = 255, message = "Name length must be smaller than 255 symbols",
      groups = {OnUpdate.class, OnCreate.class})
  private String name;

  @Schema(description = "Employee surname", example = "Doe")
  @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
  @Length(max = 255, message = "Name length must be smaller than 255 symbols",
      groups = {OnUpdate.class, OnCreate.class})
  private String surname;

  @Schema(description = "Employee managerId", example = "2")
  @NotNull(message = "managerId must be not null", groups = OnUpdate.class)
  private int managerId;

  @Schema(description = "Employee expertId", example = "2")
  @NotNull(message = "expertId must be not null", groups = OnUpdate.class)
  private int expertId;

  @Schema(description = "Employee date of next assessment", example = "2024-01-12 11:00")
  @DateTimeFormat(iso = ISO.TIME)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateOfNextAssessment;

  @Schema(description = "Employee email", example = "j.dashencko@gmail.com")
  @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
  @Length(max = 255, message = "Name length must be smaller than 255 symbols",
      groups = {OnUpdate.class, OnCreate.class})
  private String login;

  @Schema(description = "Employee crypted password", example = "$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W")
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotNull(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class})
  private String password;

}
