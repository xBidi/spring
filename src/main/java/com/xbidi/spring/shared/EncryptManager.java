package com.xbidi.spring.shared;

import com.google.crypto.tink.subtle.AesGcmJce;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/** @author diegotobalina created on 13/08/2020 */
@Getter
@ToString
@EqualsAndHashCode
public class EncryptManager {

  private byte[] key;
  private String aad;

  public EncryptManager() throws NoSuchAlgorithmException {
    this.key = getSecureKey();
    this.aad = new String(getSecureKey(), StandardCharsets.UTF_8);
  }

  public EncryptManager(byte[] key, String aad) {
    this.key = key;
    this.aad = aad;
  }

  public String encrypt(String string) throws GeneralSecurityException {
    AesGcmJce agjEncryption = new AesGcmJce(key);
    byte[] encrypted = agjEncryption.encrypt(string.getBytes(), aad.getBytes());
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public String decrypt(String string) throws GeneralSecurityException {
    AesGcmJce agjDecryption = new AesGcmJce(key);
    byte[] bytes = Base64.getDecoder().decode(string);
    byte[] decrypt = agjDecryption.decrypt(bytes, aad.getBytes());
    return new String(decrypt, StandardCharsets.UTF_8);
  }

  public boolean isEncrypted(String string) {
    String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";
    return string.matches(regex);
  }

  private byte[] getSecureKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(256); // for example
    SecretKey secretKey = keyGen.generateKey();
    return secretKey.getEncoded();
  }
}
