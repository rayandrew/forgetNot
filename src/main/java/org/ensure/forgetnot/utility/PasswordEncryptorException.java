package org.ensure.forgetnot.utility;

/**
 * @author rayandrew
 */
public class PasswordEncryptorException extends Exception {
  /**
   * Constructor
   */
  public PasswordEncryptorException() {
    super();
  }

  /**
   * Constructor dengan parameter
   * @param message yang diencrypt
   * @param throwable sebuah throwable
   */
  public PasswordEncryptorException(String message, Throwable throwable) {
    super(message, throwable);
  }

  /**
   * Constructor dengan parameter
   * @param message yang diencrypt
   */
  public PasswordEncryptorException(String message) {
    super(message);
  }

  /**
   * Constructor
   * @param throwable sebuah throwable
   */
  public PasswordEncryptorException(Throwable throwable) {
    super(throwable);
  }
}
