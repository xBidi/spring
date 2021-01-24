package com.xbidi.spring.shared;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

/** @author diegotobalina created on 13/08/2020 */
@Getter
@ToString
@EqualsAndHashCode
public class JwtManager {

  private byte[] key;

  public JwtManager(byte[] key) {
    this.key = key;
  }

  public String generateBearerJwt(
      String id, String subject, Map<String, Object> claims, long expirationIn) {
    Date issuedAt = new Date(System.currentTimeMillis());
    Date expiration = new Date(System.currentTimeMillis() + expirationIn);
    return generateBearerJwt(id, subject, claims, issuedAt, expiration);
  }

  public String generateBearerJwt(
      String id, String subject, Map<String, Object> claims, Date issuedAt, Date expiration) {
    return Jwts.builder()
        .setId(id)
        .setSubject(subject)
        .setClaims(claims)
        .setIssuedAt(issuedAt)
        .setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS512, this.key)
        .compact();
  }

  public Claims getClaims(String jwt) {
    return Jwts.parser().setSigningKey(this.key).parseClaimsJws(jwt).getBody();
  }
}
