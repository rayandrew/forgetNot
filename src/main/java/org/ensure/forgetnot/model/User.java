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

  public User() {

  }

  public User(String username) {
    set("user_name", username);
  }

  public static boolean createUser(
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
    logger.info(
        "Creating user "
            + userName
            + "to table Users, firstname = "
            + firstName
            + "lastname = "
            + lastName
            + "email = "
            + userEmail
            + "joindate = "
            + joinDate
            + "profile pic ="
            + profilePic
    );
    return e.saveIt();
  }

  public static User selectUser(String username) {
    User e = User.findFirst("user_name = ?", username);
    logger.info("User : " + e.getString("user_name"));
    return e;
  }

  public static boolean deleteUser(String username) {
    User e = User.findFirst("user_name = ?", username);
    logger.info(
        "Deleting user " + username + "from table Users"
    );
    return e.delete();
  }

  public static boolean updateUser(String username, String columnName, String value) {
    logger.info(
        "Change from table Users => username : "
            + username
            + " , column "
            + columnName
            + " to "
            + value
    );
    return User.findFirst("user_name = ?", username)
        .set(columnName, value)
        .saveIt();
  }

  public static String getAttribute(String columnName, String username) {
    User u = User.findFirst(
        "user_name = ?",
        username
    );

    logger.info(
        "Get attribute table Users => username : "
            + username
            + " column "
            + columnName
            + " value "
            + u.getString(columnName)
    );

    return u.getString(columnName);
  }

  public static boolean deleteAllUsers() {
    logger.info(
        "Delete all users from table Users"
    );
    return User.deleteAllUsers();
  }
}
