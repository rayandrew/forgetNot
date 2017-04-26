package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Config;
import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.view.ActivityView;

import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * class ActivityController.
 *
 * @author aldrich
 */
public class ActivityController extends Controller {
  private List<Reminder> activities;
  private ActivityView viewer;

  /**
   * Constructor.
   */
  public ActivityController() {
    Database.connect();
    activities = Reminder.getAllReminderFromUser(Config.getLoginUser());

    String[][] temp = new String[activities.size()][4];
    for (int idx = 0; idx < activities.size(); idx++) {
      temp[idx][0] = activities.get(idx).get("reminder_id").toString();
      temp[idx][1] = (String) activities.get(idx).get("reminder_title");
      temp[idx][2] = (String) activities.get(idx).get("content");
      temp[idx][3] = activities.get(idx).get("due_time").toString();
    }

    viewer = new ActivityView(temp);
    Database.close();
  }

  /**
   * Menambah activity seorang user.
   *
   * @param activityDescription String yang menampung informasi untuk dimasukkan kedalam database
   */
  public static boolean addActivity(String[] activityDescription) {
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
    boolean status = Reminder.createReminder(title, username, content, timeCreate, timeDue);
    Database.close();
    return status;
  }

  /**
   * Refresh view setelah menambahkan/menghapus suatu reminder.
   *
   * @return test mengembalikan sebuah matrix of Object yang akan ditampilkan ke layar
   */
  public static Object[][] refresh() {
    Database.connect();
    List<Reminder> update = Reminder.getAllReminderFromUser(Config.getLoginUser());

    String[][] test = new String[update.size()][4];
    for (int idx = 0; idx < update.size(); idx++) {
      test[idx][0] = (String) update.get(idx).get("reminder_id");
      test[idx][1] = (String) update.get(idx).get("reminder_title");
      test[idx][2] = (String) update.get(idx).get("content");
      test[idx][3] = update.get(idx).get("due_time").toString();
    }

    Database.close();
    return test;
  }

  @Override
  public Component init() {
    return viewer.init();
  }
}
