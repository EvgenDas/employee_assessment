package com.dashenckoevgeny.spring.springboot.employee_assessment.web.security;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class JwtEntity implements UserDetails {

  private int id;
  private final String username;
  private final String name;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
