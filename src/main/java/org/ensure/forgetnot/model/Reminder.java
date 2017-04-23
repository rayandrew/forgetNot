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
      String reminderUser,
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
      String reminderUser,
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

  public static void selectReminder(String username, int id) {
    Reminder e = Reminder.findFirst(
        "reminder_user = ? and reminder_id = ?",
        username,
        id
    );
    logger.info(e.getString("reminder_title"));
  }

  public static void getAllReminderFromUser(String username) {
    Reminder.find("reminder_user =" + "\"" + username + "\"",
        e -> logger.info(e.getString("reminder_title"))
    );
  }

  public static void deleteReminder(String username, int id) {
    Reminder e = Reminder.findFirst(
        "reminder_user = ? and reminder_id = ?",
        username,
        id
    );
    e.delete();
  }

  public static void updateReminder(
      String username,
      int id,
      String columnName,
      String value
  ) {
    Reminder.findFirst("reminder_user = ? and reminder_id = ?",
        username,
        id
    ).set(columnName, value).saveIt();
  }

  public static String getAttribute(
      String username,
      int id,
      String columnName
  ) {
    return Reminder.findFirst("reminder_user = ? and reminder_id = ?",
        username,
        id
    ).getString(columnName);
  }

  public static void deleteAllReminders() {
    Reminder.deleteAll();
  }

  public static void selectAllReminders() {
    logger.info("Reminder list: " + Reminder.findAll());
  }
}
