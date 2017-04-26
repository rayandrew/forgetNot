package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Config;
import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.User;
import org.ensure.forgetnot.utility.PasswordEncryptor;
import org.ensure.forgetnot.utility.PasswordEncryptorException;
import org.ensure.forgetnot.view.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Component;

/**
 * class LoginController.
 * @author girvandi
 */
public class LoginController extends Controller {
  static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  /**
   * Constructor.
   */
  public LoginController() {
    view = new LoginView("Login");
    show = true;
  }

  /**
   * Method untuk verifikasi login.
   * @param username Username user yang akan diverifikasi passwordnya
   * @param password password yang dimasukkan oleh user
   * @return loginStatus sebuah boolean yang menunjukkan apakah password yang dimasukkan cocok dengan password dalam database
   */
  public static boolean verifyLogin(String username, String password) {
    Database.connect();
    User u = User.findFirst("user_name = ?", username);
    boolean loginStatus = false;
    try {
      loginStatus = u.getString("password")
          .equals(
              PasswordEncryptor.generateMd5(
                  password
              )
          );
      logger.info("Login status = " + loginStatus);
    } catch (PasswordEncryptorException e) {
      e.printStackTrace();
    }
    Database.close();
    if(loginStatus){
      Config.setLoginUser(username);
    }
    return loginStatus;
  }

  @Override
  public Component init() {
    return view.init();
  }
}
