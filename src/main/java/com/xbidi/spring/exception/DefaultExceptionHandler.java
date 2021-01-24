package com.xbidi.spring.exception;

import com.xbidi.spring.shared.PowerException;
import com.xbidi.spring.shared.dto.CustomErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** @author diegotobalina created on 24/06/2020 */
@ControllerAdvice
public class DefaultExceptionHandler {

  @ResponseBody
  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  private CustomErrorDTO exception(
      HttpServletRequest request, HttpServletResponse response, Exception ex) {
    PowerException powerException = new PowerException(ex);
    powerException.printStackTrace(); // for debugging
    return new CustomErrorDTO(powerException);
  }
}
