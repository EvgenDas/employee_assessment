package com.dashenckoevgeny.spring.springboot.employee_assessment.web.security;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.AccessDeniedException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.EmployeeService;
import com.dashenckoevgeny.spring.springboot.employee_assessment.service.props.JwtProperties;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.auth.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;

  private final UserDetailsService userDetailsService;
  private final EmployeeService employeeService;
  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
  }

  public String createAccessToken(int userId, String username, Set<Role> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("id", userId);
    claims.put("roles", resolveRoles(roles));
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtProperties.getAccess());
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key)
        .compact();
  }

  private List<String> resolveRoles(Set<Role> roles) {
    return roles.stream()
        .map(Enum::name)
        .collect(Collectors.toList());
  }

  public String createRefreshToken(int userId, String username) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("id", userId);
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtProperties.getRefresh());
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key)
        .compact();
  }

  public JwtResponse refreshUserToken(String refreshToken) {
    JwtResponse jwtResponse = new JwtResponse();
    if (!validateToken(refreshToken)) {
      throw new AccessDeniedException();
    }
    int employeeId = Integer.parseInt(getId(refreshToken));
    Employee employee = employeeService.getById(employeeId);
    jwtResponse.setId(employeeId);
    jwtResponse.setUsername(employee.getLogin());
    jwtResponse.setAccessToken(createAccessToken(employeeId,
        employee.getLogin(), employee.getRoles()));
    jwtResponse.setRefreshToken(createRefreshToken(employeeId, employee.getLogin()));
    return jwtResponse;
  }

  public boolean validateToken(String token) {
    Jws<Claims> claims = Jwts
        .parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token);
    return !claims.getBody().getExpiration().before(new Date());
  }

  private String getId(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("id")
        .toString();
  }

  private String getUsername(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public Authentication getAuthentication(String token) {
    String username = getUsername(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

}
