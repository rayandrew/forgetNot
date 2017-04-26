package org.ensure.forgetnot.controller;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class LoginControllerTest {
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