package org.ensure.forgetnot.model;

import org.ensure.forgetnot.MainClass;
import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.core.DatabaseDaemon;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class UserTest {
  static final Logger logger = LoggerFactory.getLogger(UserTest.class);

  @Test
  public void createUser() throws Exception {
    /*DatabaseDaemon.createNewDatabase("root","","forgetnot");
    DatabaseDaemon.initializeTable("root","","forgetnot");
    Database.connect();
    User.createUser(
        "test",
        "test",
        "test",
        "test",
        "test",
        "test",
        "test"
    );
    Database.close();*/
  }

  @Test
  public void selectUser() throws Exception {
  }

  @Test
  public void deleteUser() throws Exception {
  }

  @Test
  public void updateUser() throws Exception {
  }

  @Test
  public void getAttribute() throws Exception {
  }

  @Test
  public void deleteAllUsers() throws Exception {
  }

}