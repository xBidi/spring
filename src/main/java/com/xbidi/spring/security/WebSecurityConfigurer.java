package com.xbidi.spring.security;

import com.xbidi.spring.shared.PowerException;
import com.xbidi.spring.shared.PowerResponse;
import com.xbidi.spring.shared.dto.CustomErrorDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** @author diegotobalina created on 24/06/2020 */
@Slf4j
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

  private AuthenticationFilter authenticationFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();
    http.httpBasic().disable();
    http.formLogin().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.exceptionHandling().authenticationEntryPoint(this::unauthorized);

    // TODO CUSTOM CORS
    http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

    http.antMatcher("/api/**")
        .authorizeRequests()
        .anyRequest()
        // .authenticated()
        .permitAll() // TODO AUTHENTICATE
        .and()
        .addFilterBefore(authenticationFilter, AnonymousAuthenticationFilter.class);
  }

  @SneakyThrows
  private void unauthorized(
      HttpServletRequest req, HttpServletResponse res, AuthenticationException e) {
    PowerResponse powerResponse = new PowerResponse(res);
    PowerException powerException = new PowerException(e);
    log.warn(powerException.getFullMessage());
    CustomErrorDTO customErrorDTO = new CustomErrorDTO(powerException);
    powerResponse.sendJson(customErrorDTO, HttpStatus.UNAUTHORIZED.value());
  }
}
