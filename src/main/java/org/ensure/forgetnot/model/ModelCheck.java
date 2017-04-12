package org.ensure.forgetnot.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by rayandrew on 4/12/2017.
 */
public class ModelCheck {
  public ModelCheck() {

  }

  public static void createNewDatabase(String fileName) {

    String url = "jdbc:sqlite:./data/" + fileName;

    try (Connection conn = DriverManager.getConnection(url)) {
      if (conn != null) {
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("A new database has been created.");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void createNewTable(String fileName) {
    // SQLite connection string
    String url = "jdbc:sqlite:./data/" + fileName;

    String sql = "CREATE TABLE employees (\n"
        + "      id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n"
        + "      first_name VARCHAR(56),\n"
        + "      last_name VARCHAR(56)\n"
        + "  );\n";

    try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
      // create a new table
      stmt.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private Connection connect(String fileName) {
    // SQLite connection string
    String url = "jdbc:sqlite:./data/" + fileName;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public void insert(String name, double capacity) {
    String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

    try (Connection conn = this.connect("test.db");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, name);
      pstmt.setDouble(2, capacity);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * @param args the command line arguments
   */
  /**
  public static void main(String[] args) {
    createNewDatabase("test.db");
    createNewTable("test.db");

    ModelCheck app = new ModelCheck();
    // insert three new rows
    app.insert("Raw Materials", 3000);
    app.insert("Semifinished Goods", 4000);
    app.insert("Finished Goods", 5000);
  }
   */

}
