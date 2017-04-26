package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.User;
import org.ensure.forgetnot.utility.PasswordEncryptor;
import org.ensure.forgetnot.utility.PasswordEncryptorException;
import org.ensure.forgetnot.view.RegisterView;

import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class RegisterController.
 *
 * @author girvandi
 */
public class RegisterController extends Controller {
  /**
   * Constructor.
   */
  public RegisterController() {
    view = new RegisterView("Register");
    show = false;
  }

  /**
   * Method untuk registrasi user.
   *
   * @param username   username yang akan di register
   * @param password   password yang akan di register
   * @param firstname first name yang di register
   * @param lastname last name yang di register
   * @param useremail email yang diregister
   */
  public void registerUser(
      String username,
      String password,
      String firstname,
      String lastname,
      String useremail
  ) {
    Database.connect();
    if (!User.findFirst("user_name = ?", username).exists()) {
      try {
        User.createUser(username,
            PasswordEncryptor.generateMd5(password),
            firstname,
            lastname,
            useremail,
            LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            ),
            null);
      } catch (PasswordEncryptorException e) {
        e.printStackTrace();
      }
    }
    Database.close();
  }

  @Override
  public Component init() {
    return view.init();
  }
}
