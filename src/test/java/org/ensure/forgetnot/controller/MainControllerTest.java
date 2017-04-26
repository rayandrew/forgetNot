package org.ensure.forgetnot.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class MainControllerTest {
  public MainControllerTest() {

  }

  @Test
  public void createMain() throws Exception {
    MainController mc = new MainController();
    assertEquals(mc.isShow(),true);
  }

}