package org.ensure.forgetnot.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rufus on 4/13/2017.
 */
@IdName("reminder_id")
public class Reminder extends Model {
  static final Logger logger = LoggerFactory.getLogger(Reminder.class);

  static {
    validatePresenceOf(
        "reminder_title",
        "reminder_user",
        "priority",
        "content",
        "created_time",
        "due_time"
    );
  }

  public Reminder() {

  }

  public Reminder(String title) {
    set("reminder_title", title);
  }

  public static void createReminder(
      String reminderTitle,
      int reminderUser,
      String content,
      String createdTime,
      String dueTime
  ) {
    Reminder e = new Reminder(reminderTitle);
    e.set("reminder_user", reminderUser);
    e.set("content", content);
    e.set("created_time", createdTime);
    e.set("due_time", dueTime);
    e.set("priority", false);
    e.saveIt();
  }

  public static void createReminder(
      String reminderTitle,
      int reminderUser,
      String content,
      String createdTime,
      String dueTime,
      boolean priority
  ) {
    Reminder e = new Reminder(reminderTitle);
    e.set("reminder_user", reminderUser);
    e.set("content", content);
    e.set("created_time", createdTime);
    e.set("priority", priority);
    e.set("due_time", dueTime);
    e.saveIt();
  }

  public static void selectReminder(int id) {
    Reminder e = Reminder.findFirst("reminder_id = ?", id);
    logger.info(e.getString("reminder_title"));
  }

  public static void getAllReminderFromUser(int userId) {
    Reminder.find("reminder_user =" + userId,
        e -> logger.info(e.getString("reminder_title"))
    );
  }

  public static void deleteReminder(int id) {
    Reminder e = Reminder.findFirst("reminder_id = ?", id);
    e.delete();
  }

  public static void updateReminder(int id, String columnName, String value) {
    Reminder.findFirst("reminder_id = ?", id).set(columnName, value).saveIt();
  }

  public static String getAttribute(String columnName, int id) {
    return Reminder.findFirst("reminder_id = ?", id).getString(columnName);
  }

  public static void deleteAllUsers() {
    Reminder.deleteAll();
  }

  public static void selectAllUsers() {
    logger.info("User list: " + Reminder.findAll());
  }
}
