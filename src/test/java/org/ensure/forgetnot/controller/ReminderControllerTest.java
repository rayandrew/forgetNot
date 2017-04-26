package org.ensure.forgetnot.controller;

//import org.ensure.forgetnot.controller.ReminderController;

import org.ensure.forgetnot.core.Database;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class ReminderControllerTest {

  public ReminderControllerTest() {

  }

  @Test
  public void makeTest() {
    Database.connect();
    ReminderController rc = new ReminderController();
    Assert.assertNotNull(rc);
    Database.close();
  }

  @Test
  public void init() throws Exception {
    Database.connect();
    ReminderController rc = new ReminderController();
    Assert.assertNotNull(rc.init());
    Database.close();
  }

}