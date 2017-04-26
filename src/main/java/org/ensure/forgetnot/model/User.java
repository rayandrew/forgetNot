package org.ensure.forgetnot.model;

import org.ensure.forgetnot.utility.PasswordEncryptor;
import org.ensure.forgetnot.utility.PasswordEncryptorException;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kelas Model User.
 *
 * @author Ray
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

  /**
   * Konstruktor User.
   */
  public User() {

  }

  /**
   * Konstruktor dengan parameter.
   *
   * @param username nama user yang akan disimpan di database
   */
  public User(String username) {
    set("user_name", username);
  }

  /**
   * Method untuk membuat user baru.
   *
   * @param firstName  nama depan
   * @param joinDate   tanggal join
   * @param lastName   nama belakang
   * @param password   password
   * @param profilePic gambar
   * @param userEmail  email
   * @param userName   username unik
   * @return mengembalikan apakah pembuatan berhasil atau tidak
   */
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
    try {
      e.set("password", PasswordEncryptor.generateMd5(password));
    } catch (PasswordEncryptorException e1) {
      e1.printStackTrace();
    }
    e.set("first_name", firstName);
    e.set("last_name", lastName);
    e.set("user_email", userEmail);
    e.set("join_date", joinDate);
    e.set("profile_pic", profilePic);
    logger.info(
      "Creating user "
        + userName
        + " to table Users, firstname = "
        + firstName
        + " lastname = "
        + lastName
        + " email = "
        + userEmail
        + " joindate = "
        + joinDate
        + " profile pic ="
        + profilePic
    );
    return e.saveIt();
  }

  /**
   * Method untuk membuat user baru.
   *
   * @param firstName nama depan
   * @param joinDate  tanggal join
   * @param lastName  nama belakang
   * @param password  password
   * @param userEmail email
   * @param userName  username unik
   * @return mengembalikan apakah pembuatan berhasil atau tidak
   */
  public static boolean createUser(
    String userName,
    String password,
    String firstName,
    String lastName,
    String userEmail,
    String joinDate
  ) {
    User e = new User();
    e.set("user_name", userName);
    try {
      e.set("password", PasswordEncryptor.generateMd5(password));
    } catch (PasswordEncryptorException e1) {
      e1.printStackTrace();
    }
    e.set("first_name", firstName);
    e.set("last_name", lastName);
    e.set("user_email", userEmail);
    e.set("join_date", joinDate);
    e.set("profile_pic", "empty");
    logger.info(
      "Creating user "
        + userName
        + " to table Users, firstname = "
        + firstName
        + " lastname = "
        + lastName
        + " email = "
        + userEmail
        + " joindate = "
        + joinDate
        + " profile pic = empty"
    );
    return e.saveIt();
  }

  /**
   * Mengambil User.
   *
   * @param username Username yang diinginkan
   * @return kelas User yang diambil
   */
  public static User selectUser(String username) {
    User e = User.findFirst("user_name = ?", username);
    logger.info("User : " + e.getString("user_name"));
    return e;
  }

  /**
   * Menghapus user.
   *
   * @param username username yang akan dihapus
   * @return apakah User berhasil dihapus
   */
  public static boolean deleteUser(String username) {
    User e = User.findFirst("user_name = ?", username);
    logger.info(
      "Deleting user " + username + "from table Users"
    );
    return e.delete();
  }

  /**
   * Mengupdate User.
   *
   * @param username   username yang ingin diganti
   * @param columnName kolom yang ingin diupdate
   * @param value      nilai yang ingin dimasukan
   * @return apakah update berhasil
   */
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

  /**
   * Mengambil atribut user.
   *
   * @param username   username yang bersangkutan
   * @param columnName nama field
   * @return atribut user
   */
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

  /**
   * Menghapus seluruh user.
   *
   * @return apakah penghapusan berhasil
   */
  public static boolean deleteAllUsers() {
    logger.info(
      "Delete all users from table Users"
    );
    return User.deleteAllUsers();
  }
}
