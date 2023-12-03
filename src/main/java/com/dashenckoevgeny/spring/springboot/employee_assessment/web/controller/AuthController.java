package com.dashenckoevgeny.spring.springboot.employee_assessment.web.controller;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.AuthService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtRequest;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtResponse;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.employee.EmployeeDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnCreate;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers.EmployeeMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

  private final AuthService authService;
  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;

  @PostMapping("/login")
  public JwtResponse login(@Validated @RequestBody final JwtRequest loginRequest){
    return authService.login(loginRequest);
  }

  @PostMapping("/register")
  public EmployeeDto register(@Validated(OnCreate.class) @RequestBody final EmployeeDto employeeDto) {
    Employee employee = employeeMapper.toEntity(employeeDto);
    Employee createdEmployee = employeeService.create(employee);
    return employeeMapper.toDto(createdEmployee);
  }

  @PostMapping("/refresh")
  public JwtResponse refresh(@RequestBody final String refreshToken) {
    return authService.refresh(refreshToken);
  }

}
