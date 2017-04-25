package org.ensure.forgetnot.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    e.set("priority", 1);
    logger.info(
        "Creating reminder "
            + reminderTitle
            + "to table Reminders, reminder user = "
            + reminderUser
            + "content = "
            + content
            + "created time = "
            + createdTime
            + "due time = "
            + dueTime
            + "priority = 1"
    );
    e.saveIt();
  }

  public static void createReminder(
      String reminderTitle,
      String reminderUser,
      String content,
      String createdTime,
      String dueTime,
      int priority
  ) {
    Reminder e = new Reminder(reminderTitle);
    e.set("reminder_user", reminderUser);
    e.set("content", content);
    e.set("created_time", createdTime);
    e.set("priority", priority);
    e.set("due_time", dueTime);
    logger.info(
        "Creating reminder "
            + reminderTitle
            + "to table Reminders, reminder user = "
            + reminderUser
            + "content = "
            + content
            + "created time = "
            + createdTime
            + "due time = "
            + dueTime
            + "priority = "
            + priority
    );
    e.saveIt();
  }

  public static Reminder selectReminder(String username, int id) {
    Reminder e = Reminder.findFirst(
        "reminder_user = ? and reminder_id = ?",
        username,
        id
    );
    logger.info(
        "Reminder title = "
            + e.getString("reminder_title")
            + " , user : "
            + username
    );
    return e;
  }

  public static List<Reminder> getAllReminderFromUser(String username) {
    List<Reminder> reminderList = Reminder
        .where("reminder_user =" + "\"" + username + "\"")
        .orderBy("reminder_id asc");

    for (Reminder reminder : reminderList) {
      logger.info(reminder.getString("reminder_title"));
    }
    return reminderList;
  }

  public static boolean deleteReminder(String username, int id) {
    Reminder e = Reminder.findFirst(
        "reminder_user = ? and reminder_id = ?",
        username,
        id
    );

    logger.info(
        "Deleting reminder id " + id + ", username " + username + "from table Reminders"
    );

    return e.delete();
  }

  public static boolean updateReminder(
      String username,
      int id,
      String columnName,
      String value
  ) {
    logger.info(
        "Change from table Reminder => username : "
            + username
            + " , column "
            + columnName
            + " to "
            + value
    );
    return Reminder
        .findFirst("reminder_user = ? and reminder_id = ?",
            username,
            id
        ).set(
            columnName, value
        ).saveIt();
  }

  public static String getAttribute(
      String username,
      int id,
      String columnName
  ) {
    Reminder e = Reminder.findFirst("reminder_user = ? and reminder_id = ?",
        username,
        id
    );
    logger.info(
        "Get attribute table Reminders => username : "
            + username
            + " column "
            + columnName
            + " value "
            + e.getString(columnName)
    );

    return e.getString(columnName);
  }
}
