package org.ensure.forgetnot.controller;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class MainControllerTest {
  public MainControllerTest() {

  }

  @Test
  public void createMain() throws Exception {
    MainController mc = new MainController();
    Assert.assertEquals(mc.isShow(), true);
  }

}