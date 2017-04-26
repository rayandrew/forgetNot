package org.ensure.forgetnot.core;

/**
 * class DatabaseDaemonException.
 * @author Ray
 */
public class DatabaseDaemonException extends Exception {
  /**
   * Konstruktor tanpa parameter.
   * */
  public DatabaseDaemonException() {
    super();
  }

  /**
   * Kontruktor dengan parameter.
   * @param message pesan error
   * @param throwable Kelas Exception
   * */
  public DatabaseDaemonException(String message, Throwable throwable) {
    super(message, throwable);
  }

  /**
   * Konstukror.
   * @param message pesan error
   * */
  public DatabaseDaemonException(String message) {
    super(message);
  }

  /**
   * Konstruktor.
   * @param throwable Kelas Exception
   * */
  public DatabaseDaemonException(Throwable throwable) {
    super(throwable);
  }
}
