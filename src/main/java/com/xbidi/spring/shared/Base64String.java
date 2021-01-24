package com.xbidi.spring.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/** @author diegotobalina created on 13/08/2020 */
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Base64String {

  private String content;

  public Base64String(String content) {
    this.content = content;
  }

  public void toBase64() {
    this.content = Base64.getEncoder().encodeToString(this.content.getBytes());
  }

  public void toUtf8() {
    Base64.getDecoder().decode(this.content);
    this.content = new String(this.content.getBytes(), StandardCharsets.UTF_8);
  }

  public boolean isBase64() {
    String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";
    return this.content.matches(regex);
  }
}
