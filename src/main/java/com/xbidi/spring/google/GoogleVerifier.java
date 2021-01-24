package com.xbidi.spring.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

/** @author diegotobalina created on 24/06/2020 */
@Component
public class GoogleVerifier {

  private String clientId = "<code>.apps.googleusercontent.com"; // TODO REPLACE CLIENT ID
  private NetHttpTransport netHttpTransport;
  private JacksonFactory jacksonFactory;

  /** Returns null if the token is not valid */
  public GoogleIdToken.Payload verify(String jwt) throws GeneralSecurityException, IOException {
    if (netHttpTransport == null) netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
    if (jacksonFactory == null) jacksonFactory = JacksonFactory.getDefaultInstance();
    GoogleIdTokenVerifier verifier = getVerifier();
    GoogleIdToken verify = verifier.verify(jwt);
    if (verify == null) return null;
    return verify.getPayload();
  }

  private GoogleIdTokenVerifier getVerifier() {
    return new GoogleIdTokenVerifier.Builder(this.netHttpTransport, this.jacksonFactory)
        .setAudience(Collections.singletonList(this.clientId))
        .build();
  }
}
