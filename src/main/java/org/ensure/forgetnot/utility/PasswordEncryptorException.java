package org.ensure.forgetnot.utility;

/**
 * Created by rufus on 4/12/2017.
 */
public class PasswordEncryptorException extends Exception {
  public PasswordEncryptorException() {
    super();
  }

  public PasswordEncryptorException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public PasswordEncryptorException(String message) {
    super(message);
  }

  public PasswordEncryptorException(Throwable throwable) {
    super(throwable);
  }
}
