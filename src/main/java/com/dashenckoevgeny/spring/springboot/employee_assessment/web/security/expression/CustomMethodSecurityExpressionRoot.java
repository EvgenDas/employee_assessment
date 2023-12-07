package com.dashenckoevgeny.spring.springboot.employee_assessment.web.security.expression;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.security.JwtEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements
    MethodSecurityExpressionOperations {

  private Object filterObject;
  private Object returnObject;
  private Object target;
  private HttpServletRequest request;

  private EmployeeService employeeService;

  /**
   * Creates a new instance
   *
   * @param authentication the {@link Authentication} to use. Cannot be null.
   */
  public CustomMethodSecurityExpressionRoot(
      Authentication authentication) {
    super(authentication);
  }

  @Override
  public Object getThis() {
    return target;
  }

  public boolean canAccessEmployee(int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int employeeId = employee.getId();
    return employeeId == id || hasAnyRole(authentication, Role.ROLE_ADMIN);
  }



  private boolean hasAnyRole(Authentication authentication, Role... roles) {
    for (Role role : roles) {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
      if(authentication.getAuthorities().contains(authority)) {
        return true;
      }
    }
    return false;
  }

  public boolean canAccessAssessment(int assessmentId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtEntity employee = (JwtEntity) authentication.getPrincipal();
    int id = employee.getId();
    return employeeService.isAssessmentOwner(id, assessmentId);
    // понять, как разрешить доступ manager and expert
  }
}
