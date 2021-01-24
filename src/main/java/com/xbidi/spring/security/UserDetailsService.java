package com.xbidi.spring.security;

/** @author diegotobalina created on 24/06/2020 */
public interface UserDetailsService {

  CustomUserDetails getByToken(String token);
}
