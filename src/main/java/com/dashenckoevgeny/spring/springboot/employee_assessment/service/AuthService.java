package com.dashenckoevgeny.spring.springboot.employee_assessment.service;

import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtRequest;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtResponse;

public interface AuthService {

  JwtResponse login(JwtRequest loginRequest);

  JwtResponse refresh(String refreshToken);

}
