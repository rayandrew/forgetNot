package org.ensure.forgetnot.controller;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class LoginControllerTest {
  public LoginControllerTest() {

  }

  @Test
  public void createTest() {
    LoginController login = new LoginController();
    Assert.assertNotNull(login);
  }

  @Test
  public void initTest() {
    LoginController login = new LoginController();
    Assert.assertNotNull(login.init());
  }
}