package com.dashenckoevgeny.spring.springboot.employee_assessment.service.Impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.AuthService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtRequest;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtResponse;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final EmployeeService employeeService;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public JwtResponse login(JwtRequest loginRequest) {
    JwtResponse jwtResponse = new JwtResponse();
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),
              loginRequest.getPassword()));
    } catch (AuthenticationException e) {
      System.out.println(e);
    }
    Employee employee = employeeService.getByLogin(loginRequest.getLogin());
    jwtResponse.setId(employee.getId());
    jwtResponse.setUsername(employee.getLogin());
    jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(employee.getId(),
        employee.getLogin(), employee.getRoles()));
    jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(employee.getId(),
        employee.getLogin()));
    return jwtResponse;
  }

  @Override
  public JwtResponse refresh(String refreshToken) {
    return jwtTokenProvider.refreshUserToken(refreshToken);
  }
}
