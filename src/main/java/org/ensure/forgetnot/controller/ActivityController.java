package org.ensure.forgetnot.controller;

import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.ensure.forgetnot.core.Config;
import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.view.ActivityView;


/**
 * class ActivityController.
 *
 * @author aldrich
 */
public class ActivityController extends Controller {
  private ActivityView viewer;
  private static String[][] tabelEntry;

  static {
    Database.connect();
    List<Reminder> activities = Reminder.getAllReminderFromUser(Config.getLoginUser());
    tabelEntry = new String[activities.size()][4];
    for (int idx = 0; idx < activities.size(); idx++) {
      tabelEntry[idx][0] = activities.get(idx).get("reminder_id").toString();
      tabelEntry[idx][1] = (String) activities.get(idx).get("reminder_title");
      tabelEntry[idx][2] = (String) activities.get(idx).get("content");
      tabelEntry[idx][3] = activities.get(idx).get("due_time").toString();
    }
    Database.close();
  }

  /**
   * Constructor.
   */
  public ActivityController() {
    viewer = new ActivityView();
  }

  /**
   * Get tabel entry.
   *
   * @return tabel entry.
   */
  public static Object[][] getTabelEntry() {
    return tabelEntry;
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
    //System.out.println(timeCreate);
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
    tabelEntry = new String[update.size()][4];
    if (update.size() > 0) {
      for (int idx = 0; idx < update.size(); idx++) {
        tabelEntry[idx][0] = String.valueOf(update.get(idx).get("reminder_id"));
        tabelEntry[idx][1] = (String) update.get(idx).get("reminder_title");
        tabelEntry[idx][2] = (String) update.get(idx).get("content");
        tabelEntry[idx][3] = update.get(idx).get("due_time").toString();
      }
    }

    Database.close();
    return tabelEntry;
  }

  @Override
  public Component init() {
    return viewer.init();
  }
}
