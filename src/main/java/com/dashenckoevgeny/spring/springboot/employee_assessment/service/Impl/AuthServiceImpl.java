package com.dashenckoevgeny.spring.springboot.employee_assessment.service.Impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.service.AuthService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtRequest;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  @Override
  public JwtResponse login(JwtRequest loginRequest) {
    return null;
  }

  @Override
  public JwtResponse refresh(String refreshToken) {
    return null;
  }
}
