package com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

  @NotNull(message = "Login must be not null")
  private String login;

  @NotNull(message = "Password must be not null")
  private String password;

}
