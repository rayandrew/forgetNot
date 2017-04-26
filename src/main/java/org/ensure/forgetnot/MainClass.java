package org.ensure.forgetnot;

import com.alee.laf.WebLookAndFeel;
import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.core.DatabaseDaemon;
import org.ensure.forgetnot.core.DatabaseDaemonException;
import org.ensure.forgetnot.core.SplashScreen;
import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.model.User;
import org.ensure.forgetnot.utility.PasswordEncryptor;
import org.ensure.forgetnot.utility.PasswordEncryptorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.SwingUtilities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main Class.
 * @author rayandrew
 */
public class MainClass {
  static final Logger logger = LoggerFactory.getLogger(MainClass.class);

  /**
   * Main program.
   * @param args argumen
   */
  public static void main(String[] args) {

    String timeStamp = LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    );

    System.out.println(timeStamp);
    try {
      DatabaseDaemon.createNewDatabase("root", "", "forgetNot");
      DatabaseDaemon.initializeTable("root", "", "forgetNot");
    } catch (DatabaseDaemonException e) {
      e.printStackTrace();
    } finally {
      Database.connect();
      try {
        User.createUser(
            "rayandrew",
            PasswordEncryptor.generateMd5("testPassword"),
            "Ray",
            "Andrew",
            "raydreww@gmail.com",
            timeStamp,
            "lel"
        );
      } catch (PasswordEncryptorException e) {
        e.printStackTrace();
      }
      Reminder.createReminder(
          "Testing123",
          "rayandrew",
          "Testing to input something to database! LOLLLL",
          timeStamp,
          timeStamp,
          1
      );
      Reminder.createReminder(
          "LELLLLL",
          "rayandrew",
          "Anything but not good wew",
          timeStamp,
          timeStamp,
          2
      );
      User.updateUser("rayandrew", "first_name", "aldrich");
      User.selectUser("rayandrew");
      Reminder.getAllReminderFromUser("rayandrew");
      Reminder.selectReminder("rayandrew", 1);
      Database.close();
    }

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // Install WebLaF as application L&F
        WebLookAndFeel.install();
        SplashScreen.execute = new SplashScreen("./image/forget.png");
      }
    });
  }
}
