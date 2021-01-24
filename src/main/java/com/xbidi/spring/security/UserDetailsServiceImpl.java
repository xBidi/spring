package com.xbidi.spring.security;

import org.springframework.stereotype.Component;

/** @author diegotobalina created on 24/06/2020 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  /**
   * @param token Authorization header, will never be null or empty
   * @return CustomAuthentication if token verification goes ok, null if something fails
   */
  @Override
  public CustomUserDetails getByToken(String token) {
    // TODO IMPLEMENT
    return null;
  }
}
