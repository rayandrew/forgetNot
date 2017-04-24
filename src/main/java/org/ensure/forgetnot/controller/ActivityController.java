package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.view.ActivityView;
import org.javalite.activejdbc.Base;

import java.awt.Component;
import java.util.List;

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityController extends Controller {
  private List<Reminder> activities;

  public ActivityController() {
    String username = "rayandrew";
    Base.open("com.mysql.cj.jdbc.Driver",
        "jdbc:mysql://localhost/forgetNot?nullNamePatternMatchesAll=true",
        "root",
        ""
    );
    activities = Reminder.where("reminder_user ?", username);

    //System.out.println(activities.size());
    view = new ActivityView(5);
    Base.close();
  }

  public void AddActivity(String username) {
    String title = ""; //ambil title dari textbox masukan
    String content = "";
    String timeCreate = "";
    String timeDue = "";
    Reminder newReminder = new Reminder(title);
    newReminder.createReminder(title, username, content, timeCreate, timeDue);
  }

  @Override
  public Component init() {
    return view.init();
  }
}
