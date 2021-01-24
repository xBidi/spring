package com.xbidi.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** @author diegotobalina created on 24/06/2020 */
@Component
public class AuthenticationManager {

  private UserDetailsService userDetailsService;

  public AuthenticationManager(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  public boolean isAuthenticated() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    if (authentication == null) return false;
    if (!authentication.isAuthenticated()) return false;
    return !authentication.getPrincipal().equals("anonymousUser");
  }

  public void authenticate(HttpServletRequest req) {
    if (isAuthenticated()) return;
    // Verify the token
    String authorization = req.getHeader("Authorization");
    if (authorization == null || authorization.isEmpty()) return;
    CustomUserDetails userDetails = this.userDetailsService.getByToken(authorization);
    if (userDetails == null) return;

    // Authenticate the user
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(userDetails);

    // Create a new session and add the security context.
    HttpSession session = req.getSession(true);
    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
  }

  public Authentication getAuthenticated() {
    SecurityContext context = SecurityContextHolder.getContext();
    return context.getAuthentication();
  }
}
