package org.ensure.forgetnot.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rufus on 4/13/2017.
 */
@IdName("user_id")
public class User extends Model {
  static final Logger logger = LoggerFactory.getLogger(User.class);

  public User() {

  }

  public User(String username) {
    set("user_name", username);
  }

  static {
    validatePresenceOf(
        "user_name",
        "password",
        "first_name",
        "last_name",
        "user_email",
        "join_date",
        "profile_pic"
    );
  }

  public static void createUser(
      String userName,
      String password,
      String firstName,
      String lastName,
      String userEmail,
      String joinDate,
      String profilePic
  ) {
    User e = new User();
    e.set("user_name", userName);
    e.set("password", password);
    e.set("first_name", firstName);
    e.set("last_name", lastName);
    e.set("user_email", userEmail);
    e.set("join_date", joinDate);
    e.set("profile_pic", profilePic);
    e.saveIt();
  }

  public static void selectUser(String username) {
    User e = User.findFirst("user_name = ?", username);
    logger.info(e.getString("user_name"));
  }

  public static void deleteUser(String username) {
    User e = User.findFirst("user_name = ?", "\"" + username + "\"");
    e.delete();
  }

  public static void updateUser(String username, String columnName, String value) {
    User.findFirst("user_name = ?", username).set(columnName, value).saveIt();
  }

  public static String getAttribute(String columnName, String username) {
    return User.findFirst("user_name = ?", username).getString(columnName);
  }

  public static void deleteAllUsers() {
    User.deleteAll();
  }

  public static void selectAllUsers() {
    logger.info("User list: " + User.findAll());
  }
}
