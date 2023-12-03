package com.dashenckoevgeny.spring.springboot.employee_assessment.web.security;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtEntityFactory {

  public static JwtEntity create(Employee employee) {
    return new JwtEntity(
        employee.getId(),
        employee.getLogin(),
        employee.getName(),
        employee.getPassword(),
        mapToGrantedAuthorities(new ArrayList<>(employee.getRoles()))
    );
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(final List<Role> roles) {
    return roles.stream()
        .map(Enum::name)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }
}
