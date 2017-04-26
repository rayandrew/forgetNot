package org.ensure.forgetnot.controller;

//import org.ensure.forgetnot.controller.ReminderController;

import org.ensure.forgetnot.core.Database;
import org.junit.Test;

import javax.xml.crypto.Data;

import static org.junit.Assert.assertNotNull;

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
    assertNotNull(rc);
    Database.close();
  }

  @Test
  public void init() throws Exception {
    Database.connect();
    ReminderController rc = new ReminderController();
    assertNotNull(rc.init());
    Database.close();
  }

}