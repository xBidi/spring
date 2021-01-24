package com.xbidi.spring.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author diegotobalina created on 13/08/2020 */
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class HashableString {

  private String content;

  public HashableString(String content) {
    this.content = content;
  }

  public void hash(int strength) {
    BCryptPasswordEncoder coder = new BCryptPasswordEncoder(strength);
    this.content = coder.encode(this.content);
  }

  public boolean isHashed() {
    String regex = "^\\$2[ayb]\\$.{56}$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(this.content);
    return m.matches();
  }

  public boolean doHashMatch(String rawString) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.matches(rawString, this.content);
  }
}
