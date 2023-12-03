package com.dashenckoevgeny.spring.springboot.employee_assessment.web.security;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    String bearerToken = ((HttpServletRequest) servletRequest).getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      bearerToken = bearerToken.substring(7);
    }
    try {
      if (bearerToken != null && jwtTokenProvider.validateToken(bearerToken)) {

        Authentication authentication = jwtTokenProvider.getAuthentication(bearerToken);
        if (authentication != null) {
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (ResourceNotFoundException ignored) {
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
