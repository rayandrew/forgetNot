package org.ensure.forgetnot.core;

/**
 * Created by rufus on 4/19/2017.
 */
public class Config {
  private static String loginUser;

  public static String getLoginUser() {
    return loginUser;
  }

  public static void setLoginUser(String user) {
    loginUser = user;
  }
}
