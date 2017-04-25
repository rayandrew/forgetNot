package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.utility.Pair;
import org.ensure.forgetnot.view.ActivityView;
import org.javalite.activejdbc.Base;

import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityController extends Controller {
  private List<Reminder> activities;
  private ActivityView viewer;

  public ActivityController() {
    String username = "rayandrew"; //TODO: nanti ambil secara static
    Database.connect();
    activities = Reminder.getAllReminderFromUser("rayandrew");

    String[][] temp = new String[activities.size()][3];
    for(int idx = 0; idx < activities.size(); idx++){
      temp[idx][0] = (String)activities.get(idx).get("reminder_title");
      temp[idx][1] = (String)activities.get(idx).get("content");
      temp[idx][2] = activities.get(idx).get("due_time").toString();
    }

    viewer = new ActivityView(temp);
    Database.close();
  }

  public static void addActivity(String[] activityDescription) {
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

  public static Object[][] refresh(){
    String username = "rayandrew"; //TODO: nanti ambil secara static
    Database.connect();
    List<Reminder> update = Reminder.getAllReminderFromUser("rayandrew");

    String[][] test = new String[update.size()][3];
    for(int idx = 0; idx < update.size(); idx++){
      test[idx][0] = (String)update.get(idx).get("reminder_title");
      test[idx][1] = (String)update.get(idx).get("content");
      test[idx][2] = update.get(idx).get("due_time").toString();
    }

    Database.close();
    return test;
  }

  @Override
  public Component init() {
    return viewer.init();
  }
}
