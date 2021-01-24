package com.xbidi.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements Authentication {

  private String name; // username
  private Object principal;
  private Object details;
  private Object credentials;
  List<GrantedAuthority> authorities; // used in @PreAuthorize("hasRole('USER')")
  private boolean authenticated;

  /** @param authorities Must follow this patter: ['ROLE_USER','ROLE_ADMIN'...] */
  public CustomUserDetails(
      Object principal, Object credentials, List<String> authorities, Object details, String name) {

    this.credentials = credentials;
    this.details = details;
    this.principal = principal;
    this.authenticated = true;
    this.name = name;
    this.authorities = getAuthorities(authorities);
  }

  private List<GrantedAuthority> getAuthorities(List<String> authorities) {
    return authorities.stream()
        .map(role -> new SimpleGrantedAuthority(role))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public Object getCredentials() {
    return this.credentials;
  }

  @Override
  public Object getDetails() {
    return this.details;
  }

  @Override
  public Object getPrincipal() {
    return this.principal;
  }

  @Override
  public boolean isAuthenticated() {
    return this.authenticated;
  }

  @Override
  public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
    this.authenticated = authenticated;
  }

  @Override
  public String getName() {
    return this.name;
  }
}
