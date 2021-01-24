package com.xbidi.spring.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Date;
import java.util.UUID;

/** @author diegotobalina created on 13/08/2020 */
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class PowerException {

  private String id;
  private Exception exception;
  private Date timestamp;

  public PowerException(Exception exception) {
    this.id = UUID.randomUUID().toString();
    this.exception = exception;
    this.timestamp = new Date();
  }

  public String getFullMessage() {
    String methodName = getMethod();
    String rootCause = ExceptionUtils.getRootCauseMessage(this.exception);
    return String.format("%s, %s", methodName, rootCause);
  }

  private String getMethod() {
    String[] classNameSplit = exception.getStackTrace()[0].getClassName().split("\\.");
    String className = classNameSplit[classNameSplit.length - 1];
    String methodName = exception.getStackTrace()[0].getMethodName();
    return String.format("%s.%s", className, methodName);
  }

  public void printStackTrace() {
    this.exception.printStackTrace();
  }
}
