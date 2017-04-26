package org.ensure.forgetnot.controller;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class ActivityControllerTest {
  public ActivityControllerTest() {

  }

  @Test
  public void createActivityControllerTest() {
    ActivityController ac = new ActivityController();
    assertNotNull(ac);
  }

  @Test
  public void init() throws Exception {
    ActivityController ac = new ActivityController();
    assertNotNull(ac.init());
  }

}