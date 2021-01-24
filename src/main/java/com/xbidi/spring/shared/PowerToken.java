package com.xbidi.spring.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/** @author diegotobalina created on 13/08/2020 */
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class PowerToken {

  private String token;
  private String type;

  public PowerToken(String type) {
    this.token = UUID.randomUUID().toString();
    this.type = type;
  }

  public PowerToken(String token, String type) {
    this.token = token;
    this.type = type;
  }

  public String getFullToken() {
    return String.format("%s:%s", token, type);
  }
}
