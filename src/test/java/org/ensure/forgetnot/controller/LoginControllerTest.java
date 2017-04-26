package org.ensure.forgetnot.controller;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class LoginControllerTest {
  public LoginControllerTest() {

  }

  @Test
  public void createTest(){
    LoginController L = new LoginController();
    assertNotNull(L);
  }

  @Test
  public void initTest(){
    LoginController L = new LoginController();
    assertNotNull(L.init());
  }
}