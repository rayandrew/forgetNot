package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Config;
import org.ensure.forgetnot.model.User;
import org.ensure.forgetnot.utility.PasswordEncryptor;
import org.ensure.forgetnot.utility.PasswordEncryptorException;
import org.ensure.forgetnot.view.LoginView;

import java.awt.Component;

/**
 * Created by rufus on 4/22/2017.
 */
public class LoginController extends Controller {
  public LoginController() {
    view = new LoginView("Login");
  }

  public boolean verifyLogin(String username, String password) {
    User u = User.findFirst("user_name = ?", username);
    boolean loginStatus = false;
    try {
      loginStatus = u.getString("password")
          .equals(
              PasswordEncryptor.generateMd5(
                  password
              )
          );
    } catch (PasswordEncryptorException e) {
      e.printStackTrace();
    }

    if (loginStatus) {
      Config.setLoginUser(username);
    }
    return loginStatus;
  }

  @Override
  public Component init() {
    return view.init();
  }
}
