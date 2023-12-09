package com.dashenckoevgeny.spring.springboot.employee_assessment.web.security.expression;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {

  private final EmployeeService employeeService;

  public boolean canAccessEmployee(int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int employeeId = employee.getId();
    return employeeId == id || employeeService.isEmployeesManager(id, employeeId)
        || employeeService.isEmployeesExpert(id, employeeId)
        || hasAnyRole(authentication, Role.ROLE_ADMIN);
  }

  public boolean canAccessEmployeeOnlyForManagerAndExpert(int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int employeeId = employee.getId();
    return employeeId == id || hasAnyRole(authentication, Role.ROLE_ADMIN);
  }


  public boolean createOnlyForAdmin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return hasAnyRole(authentication, Role.ROLE_ADMIN);
  }


  private boolean hasAnyRole(Authentication authentication, Role... roles) {
    for (Role role : roles) {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
      if (authentication.getAuthorities().contains(authority)) {
        return true;
      }
    }
    return false;
  }

  public boolean canAccessAssessment(int assessmentId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int id = employee.getId();
    return employeeService.isAssessmentOwner(id, assessmentId) || hasAnyRole(authentication,
        Role.ROLE_ADMIN);
  }

  public boolean canAccessOwnAssessment(int assessmentId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int id = employee.getId();
    return employeeService.isAssessmentOwner(id, assessmentId) || hasAnyRole(authentication,
        Role.ROLE_ADMIN);
  }

  public boolean canAccessManagerAssessment(int assessmentId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int id = employee.getId();
    return hasAnyRole(authentication, Role.ROLE_ADMIN)
        || (hasAnyRole(authentication, Role.ROLE_MANAGER)
        && employeeService.isEmployeeManagerByAssessment(id, assessmentId));
  }

  public boolean canAccessExpertAssessment(int assessmentId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int id = employee.getId();
    return hasAnyRole(authentication, Role.ROLE_ADMIN)
        || (hasAnyRole(authentication, Role.ROLE_EXPERT)
        && employeeService.isEmployeeExpertByAssessment(id, assessmentId));
  }

}
