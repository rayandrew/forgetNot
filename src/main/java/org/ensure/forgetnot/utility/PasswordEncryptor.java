package org.ensure.forgetnot.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rufus on 4/12/2017.
 */
public class PasswordEncryptor {
  private static String hashString(String message, String algorithm)
      throws PasswordEncryptorException {
    try {
      MessageDigest digest = MessageDigest.getInstance(algorithm);
      byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

      return convertByteArrayToHexString(hashedBytes);
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
      throw new PasswordEncryptorException("Could not generate hash from String", ex);
    }
  }

  private static String convertByteArrayToHexString(byte[] arrayBytes) {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < arrayBytes.length; i++) {
      stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
          .substring(1));
    }
    return stringBuffer.toString();
  }

  public static String generateMd5(String message) throws PasswordEncryptorException {
    return hashString(message, "MD5");
  }

  public static String generateSha1(String message) throws PasswordEncryptorException {
    return hashString(message, "SHA-1");
  }

  public static String generateSha256(String message) throws PasswordEncryptorException {
    return hashString(message, "SHA-256");
  }

}
