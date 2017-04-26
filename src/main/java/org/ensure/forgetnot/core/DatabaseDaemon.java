package org.ensure.forgetnot.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class DatabaseDaemon.
 * @author rayandrew
 */
public class DatabaseDaemon {
  /**
   * Constructor
   */
  public DatabaseDaemon() {

  }

  /**
   * Inisalisasi koneksi mysql.
   * @param username mysql
   * @param password mysql
   * @return connection
   * @throws DatabaseDaemonException
   */
  private static Connection connectServer(
      String username,
      String password
  ) throws DatabaseDaemonException {
    String url = "jdbc:mysql://localhost/?user=" + username;

    if(!password.isEmpty()){
      url = url + "&password=" + password;
    }

    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      throw new DatabaseDaemonException("SQL Error", e);
    }
    return conn;
  }

  /**
   * inisialisasi koneksi dengan database aplikasi.
   * @param username mysql
   * @param password mysql
   * @param databaseName nama database aplikasi
   * @return connection
   * @throws DatabaseDaemonException
   */
  private static Connection connectDatabase(
      String username,
      String password,
      String databaseName
  ) throws DatabaseDaemonException {

    String url = "jdbc:mysql://localhost/" + databaseName + "?user=" + username;

    if(!password.isEmpty()){
      url = url + "&password=" + password;
    }

    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      throw new DatabaseDaemonException("SQL Error", e);
    }
    return conn;
  }

  /**
   * Pembuatan database aplikasi.
   * @param username mysql
   * @param password mysql
   * @param databaseName nama database aplikasi
   * @throws DatabaseDaemonException
   */
  public static void createNewDatabase(
      String username,
      String password,
      String databaseName
  ) throws DatabaseDaemonException {
    String url = "jdbc:mysql://localhost/?user=" + username;

    if(!password.isEmpty()){
      url = url + "&password=" + password;
    }

    try (Connection conn = connectServer(username, password)) {
      if(conn != null){
        DatabaseMetaData meta = conn.getMetaData();
        Statement s = conn.createStatement();
        s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("A new database named" + databaseName + " has been created.");
      }
    } catch (SQLException e) {
      throw new DatabaseDaemonException("SQL Error", e);
    }
  }

  /**
   * Membuat table dalam database.
   * @param username mysql
   * @param password mysql
   * @param databaseName nama database aplikasi
   * @throws DatabaseDaemonException
   */
  public static void initializeTable(
      String username,
      String password,
      String databaseName
  ) throws DatabaseDaemonException {
    if(username.isEmpty()){
      throw new DatabaseDaemonException("[ERROR] No username when connect database");
    } else {
      String sqlIfUsersExist = "DROP TABLE IF EXISTS users;";
      String sqlCreateUsers = "CREATE TABLE users (\n"
          + "user_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
          + "user_name VARCHAR(25) NOT NULL PRIMARY KEY,\n"
          + "password VARCHAR(100) NOT NULL,\n"
          + "first_name VARCHAR(25) NOT NULL,\n"
          + "last_name VARCHAR(25) NOT NULL,\n"
          + "user_email VARCHAR(100) NOT NULL,\n"
          + "join_date DATE NOT NULL,\n"
          + "profile_pic VARCHAR(255),\n"
          + "CONSTRAINT unique_username\n"
          + "UNIQUE (user_id,user_name,user_email)\n"
          + ");";

      String sqlIfRemindersExist = "DROP TABLE IF EXISTS reminders;";
      String sqlCreateReminders = "CREATE TABLE reminders (\n"
          + "reminder_user VARCHAR(25) NOT NULL,\n"
          + "reminder_id INT(15) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
          + "reminder_title VARCHAR(50) NOT NULL,\n"
          + "priority INT(2) NOT NULL DEFAULT 0,\n"
          + "content TEXT,\n"
          + "created_time DATETIME NOT NULL,\n"
          + "due_time DATETIME NOT NULL,\n"
          + "PRIMARY KEY(reminder_id, reminder_user), \n"
          + "CONSTRAINT `reminder_user_foreign`\n"
          + "FOREIGN KEY (reminder_user) REFERENCES users (user_name)\n"
          + "ON DELETE CASCADE\n"
          + "ON UPDATE RESTRICT\n"
          + ");";
      try (Connection conn = connectDatabase(
          username,
          password,
          databaseName);
           Statement stmt = conn.createStatement()
      ) {
        stmt.execute(sqlIfRemindersExist);
        stmt.execute(sqlIfUsersExist);
        stmt.execute(sqlCreateUsers);
        stmt.execute(sqlCreateReminders);
      } catch (SQLException e) {
        throw new DatabaseDaemonException("SQL Error", e);
      }
    }
  }
}
