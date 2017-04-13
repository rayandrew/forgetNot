package org.ensure.forgetnot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.ensure.forgetnot.core.DatabaseDaemon;
import org.ensure.forgetnot.core.DatabaseDaemonException;
import org.ensure.forgetnot.model.User;
import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rayandrew on 4/12/2017.
 */
public class MainClass {
  static final Logger logger = LoggerFactory.getLogger(MainClass.class);

  public static void main(String[] args) {
    String timeStamp = LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    );
    System.out.println(timeStamp);
    try {
      DatabaseDaemon.createNewDatabase("root", "", "forgetNot");
      DatabaseDaemon.initializeTable("root", "", "forgetNot");
    } catch (DatabaseDaemonException e) {
      e.printStackTrace();
    } finally {
      Base.open("com.mysql.cj.jdbc.Driver",
          "jdbc:mysql://localhost/forgetNot?nullNamePatternMatchesAll=true",
          "root",
          ""
      );
      User.createUser(
          "rayandrew",
          "testPassword",
          "Ray",
          "Andrew",
          "raydreww@gmail.com",
          timeStamp,
          "lel"
      );
      Base.close();
    }
  }
}
