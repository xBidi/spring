package com.xbidi.spring.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** @author diegotobalina created on 24/06/2020 */
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class PowerResponse {

  private HttpServletResponse response;

  public PowerResponse(HttpServletResponse response) {
    this.response = response;
  }

  public void sendJson(Object object, int status) throws IOException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(object);
    this.response.setContentType("application/json");
    this.response.setStatus(status);
    this.response.getOutputStream().println(json);
  }
}
