package org.ensure.forgetnot.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Kelas Model Reminder.
 * @author Ray
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

  /**
   * Konstruktor.
   * */
  public Reminder() {

  }

  /**
   * Konstruktor.
   * @param title Judul reminder
   * */
  public Reminder(String title) {
    set("reminder_title", title);
  }

  /**
   * Fungsi untuk membuat reminder.
   * @param content konten reminder
   * @param createdTime tanggal dibuat
   * @param dueTime tanggal selesai
   * @param reminderTitle judul
   * @param reminderUser username
   * */
  public static boolean createReminder(
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
    return e.saveIt();
  }

  /**
   * Membuat Reminder.
   * @param reminderUser nama user
   * @param reminderTitle judul
   * @param dueTime tanggal berakhir
   * @param createdTime tanggal dibuat
   * @param content konten
   * @param priority prioritas
   * */
  public static boolean createReminder(
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
    return e.saveIt();
  }

  /**
   * Metode untuk mengambil reminder.
   *
   * @param username nama user
   * @param id id reminder
   *           @return Sebuah reminder
   * */
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

  /**
   * Metode untuk mengambil seluruh reminder.
   *
   * @param username nama user
   *                 @return sebuah list of reminder
   * */
  public static List<Reminder> getAllReminderFromUser(String username) {
    List<Reminder> reminderList = Reminder
        .where("reminder_user =" + "\"" + username + "\"")
        .orderBy("reminder_id asc");

    for (Reminder reminder : reminderList) {
      logger.info(reminder.getString("reminder_title"));
    }
    return reminderList;
  }

  /**
   * Metode untuk menghapus reminder.
   *
   * @param username username yang akan dicari
   * @param id id yang akan dihapus
   *           @return apakah berhasil dihapus
   * */
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

  /**
   * Metode untuk mengupdate reminder.
   * @return apakah update berhasl
   * */
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
    Reminder r = Reminder
        .findFirst("reminder_user = ? and reminder_id = ?",
            username,
            id
        ).set(
            columnName, value
        );
    if (r != null) {
      return r.saveIt();
    } else {
      return false;
    }
  }

  /**
   * Mengambil Atribut.
   *
   * @param id id reminder
   * @param username nama user
   * @param columnName field yang ingin diambil
   *                   @return Atribut dari reminder
   * */
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
