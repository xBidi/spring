package com.xbidi.spring.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/** @author diegotobalina created on 13/08/2020 */
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class EmailVerifier {

  private String email;

  public EmailVerifier(String email) {
    this.email = email;
  }

  private boolean doEmailExists() {
    if (!isEmail()) return false;
    try {
      Hashtable env = new Hashtable();
      env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
      DirContext dirContext = new InitialDirContext(env);
      String hostName = email.split("@")[1];
      Attributes attrs = dirContext.getAttributes(hostName, new String[] {"MX"});
      Attribute attr = attrs.get("MX");
      if ((attr == null) || (attr.size() == 0)) {
        attrs = dirContext.getAttributes(hostName, new String[] {"A"});
        attr = attrs.get("A");
        if (attr == null) return false;
      }
      return true;
    } catch (NamingException ex) {
      return false;
    }
  }

  public boolean isEmail() {
    if (email == null || email.isEmpty()) return false;
    String regex =
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    return email.matches(regex);
  }
}
