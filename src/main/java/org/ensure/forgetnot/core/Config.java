package org.ensure.forgetnot.core;

/**
 * class Config.
 *
 * @author rayandrew
 */
public class Config {
  private static String loginUser;

  /**
   * getter loginUser.
   *
   * @return loginUser user yang saat ini sedang aktif
   */
  public static String getLoginUser() {
    return loginUser;
  }

  /**
   * setter loginUser.
   *
   * @param user yang akan di set aktif
   */
  public static void setLoginUser(String user) {
    loginUser = user;
  }
}
