package com.xbidi.spring.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xbidi.spring.shared.PowerException;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

/** @author diegotobalina created on 24/06/2020 */
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class CustomErrorDTO {

  @ApiModelProperty(example = "UNAUTHORIZED")
  private String error;

  @ApiModelProperty(example = "8b55b402-6e60-44c4-ab0d-2be2fbb60845")
  private String code;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z")
  @ApiModelProperty(example = "2020-08-15 10:42:48 UTC")
  private Date timestamp;

  public CustomErrorDTO(String error, String code) {
    this.error = error;
    this.code = code;
    this.timestamp = new Date();
  }

  public CustomErrorDTO(String error) {
    this.error = error;
    this.code = UUID.randomUUID().toString();
    this.timestamp = new Date();
  }

  public CustomErrorDTO(PowerException powerException) {
    this.error = powerException.getFullMessage();
    this.code = powerException.getId();
    this.timestamp = powerException.getTimestamp();
  }
}
