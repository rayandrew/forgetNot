package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.view.ActivityView;
import org.javalite.activejdbc.Base;

import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityController extends Controller {
  private List<Reminder> activities;
  private ActivityView viewer;

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
    viewer = new ActivityView(5);
    Base.close();
  }

  public static void addActivity(String[] activityDescription) {
    for (String a : activityDescription) {
      System.out.println(a);
    }

    String username = activityDescription[0];
    String title = activityDescription[2];
    String content = activityDescription[4];
    String timeCreate = LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    );
    System.out.println(timeCreate);
    String timeDue = activityDescription[6];

    //connect to database
    Database.connect();
    Reminder.createReminder(title, username, content, timeCreate, timeDue);
    Database.close();
  }

  @Override
  public Component init() {
    return viewer.init();
  }
}
