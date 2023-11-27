package com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {

  private Integer id;
  private String username;
  private String accessToken;
  private String refreshToken;


}
