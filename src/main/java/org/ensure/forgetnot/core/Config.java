package org.ensure.forgetnot.core;

import org.ensure.forgetnot.utility.Pair;

import java.util.ArrayList;
import java.util.List;

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
