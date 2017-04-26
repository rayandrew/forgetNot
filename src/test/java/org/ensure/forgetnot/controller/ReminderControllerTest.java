package org.ensure.forgetnot.controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class ReminderControllerTest {
  @Test
  public void makeTest(){
    ReminderController rc = new ReminderController();
    assertNotNull(rc);
  }

  @Test
  public void init() throws Exception {
    ReminderController rc = new ReminderController();
    assertNotNull(rc.init());
  }

}