package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.view.ReminderView;

import java.awt.Component;
import java.util.List;


/**
 * Created by DELL on 4/24/2017.
 */
public class ReminderController extends Controller {

  public ReminderController() {
    view = new ReminderView();
  }

  public static Integer isReminderTime(String username, String dateAndTime) {
    List<Reminder> reminders = Reminder.where("reminder_user = ?", username);
    boolean ret = false;
    Integer id = -1;
    Integer currprio = -1;
    String text = dateAndTime + ".0";
    for (Reminder r : reminders) {
      System.out.println(r.getString("due_time"));
      System.out.println(r.get("priority"));
      System.out.println(text);
      Integer temp = r.getInteger("priority");
      if (temp > currprio) {
        System.out.println("masuk1");
        if (text.equals(r.getString("due_time"))) {
          System.out.println("masuk2");
          ret = true;
          id = r.getInteger("reminder_id");
          currprio = r.getInteger("priority");
        }
      }
    }
    return id;
  }

  public Component init() {
    return view.init();
  }
}
