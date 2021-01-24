package com.xbidi.spring.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/** @author diegotobalina created on 24/06/2020 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

  @Override
  public boolean hasPermission(
      Authentication authentication, Object accessType, Object permission) {
    return true; // TODO IMPLEMENT
  }

  @Override
  public boolean hasPermission(
      Authentication authentication,
      Serializable serializable,
      String targetType,
      Object permission) {
    return true; // TODO IMPLEMENT
  }
}
