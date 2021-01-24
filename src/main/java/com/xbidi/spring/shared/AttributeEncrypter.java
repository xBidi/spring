package com.xbidi.spring.shared;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

/**
 * Attribute encryption
 *
 * @author diegotobalina
 */
@Component
public class AttributeEncrypter implements AttributeConverter<String, String> {

  // TODO REPLACE KEY
  private String key = "0000000000000000000000000000000F";
  // TODO REPLACE AAD
  private String aad = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
  private EncryptManager encryptManager;

  @Override
  @SneakyThrows
  public String convertToDatabaseColumn(String attribute) {
    if (encryptManager == null) encryptManager = new EncryptManager(key.getBytes(), aad);
    return encryptManager.encrypt(attribute);
  }

  @Override
  @SneakyThrows
  public String convertToEntityAttribute(String dbData) {
    if (encryptManager == null) encryptManager = new EncryptManager(key.getBytes(), aad);
    return encryptManager.decrypt(dbData);
  }
}
