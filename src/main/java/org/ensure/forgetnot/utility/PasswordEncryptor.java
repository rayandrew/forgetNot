package org.ensure.forgetnot.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class Password Encryptor.
 * @author rayandrew
 */
public class PasswordEncryptor {
  /**
   * Method untuk enkripsi password.
   * @param message message yang akan direncrypt
   * @param algorithm algoritma yang dipakai untuk mengencrypt
   * @return String yang telah diencrypt
   * @throws PasswordEncryptorException
   */
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

  /**
   * Method untuk menconvert byte array ke hex string.
   * @param arrayBytes array of bytes yang akan diconvert
   * @return String yang telah diconvert
   */
  private static String convertByteArrayToHexString(byte[] arrayBytes) {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < arrayBytes.length; i++) {
      stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
          .substring(1));
    }
    return stringBuffer.toString();
  }

  /**
   * Method penggunaan algoritma MD5.
   * @param message message yang akan di encrypt
   * @return String yang telah diencrypt
   * @throws PasswordEncryptorException
   */
  public static String generateMd5(String message) throws PasswordEncryptorException {
    return hashString(message, "MD5");
  }

  /**
   * Method penggunaan algoritma SHA-1.
   * @param message message yang akan di encrypt
   * @return String yang telah diencrypt
   * @throws PasswordEncryptorException
   */
  public static String generateSha1(String message) throws PasswordEncryptorException {
    return hashString(message, "SHA-1");
  }

  /**
   * Method penggunaan algoritma SHA-256.
   * @param message message yang akan di encrypt
   * @return String yang telah diencrypt
   * @throws PasswordEncryptorException
   */
  public static String generateSha256(String message) throws PasswordEncryptorException {
    return hashString(message, "SHA-256");
  }

}
