package org.ensure.forgetnot;

import org.ensure.forgetnot.controller.ActivityController;
import org.ensure.forgetnot.controller.ActivityControllerTest;
import org.ensure.forgetnot.controller.LoginControllerTest;
import org.ensure.forgetnot.controller.MainControllerTest;
import org.ensure.forgetnot.controller.ReminderControllerTest;
import org.ensure.forgetnot.core.DatabaseTest;
import org.ensure.forgetnot.model.ReminderTest;
import org.ensure.forgetnot.model.UserTest;
import org.ensure.forgetnot.utility.ClockTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by rufus on 4/26/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
    ClockTest.class,
    ReminderTest.class,
    UserTest.class,
    DatabaseTest.class,
    ActivityControllerTest.class,
    LoginControllerTest.class,
    MainControllerTest.class,
    ReminderControllerTest.class
})

public class TestSuite {

}