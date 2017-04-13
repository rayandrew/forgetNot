package org.ensure.forgetnot.core;

/**
 * Created by rufus on 4/13/2017.
 */
public class DatabaseDaemonException extends Exception {
  public DatabaseDaemonException() {
    super();
  }

  public DatabaseDaemonException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public DatabaseDaemonException(String message) {
    super(message);
  }

  public DatabaseDaemonException(Throwable throwable) {
    super(throwable);
  }
}
