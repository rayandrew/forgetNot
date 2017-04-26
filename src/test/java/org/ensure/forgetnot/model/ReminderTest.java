package org.ensure.forgetnot.model;

import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.core.DatabaseDaemon;
import org.ensure.forgetnot.core.DatabaseDaemonException;
import org.ensure.forgetnot.utility.PasswordEncryptor;
import org.ensure.forgetnot.utility.PasswordEncryptorException;
import org.junit.Test;

import javax.xml.crypto.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class ReminderTest {
  private String timeStamp = LocalDateTime.now().format(
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
  );
/*
  public ReminderTest() {
    try {
      DatabaseDaemon.createNewDatabase("root", "", "forgetNot");
      DatabaseDaemon.initializeTable("root", "", "forgetNot");
    } catch (DatabaseDaemonException e) {
      e.printStackTrace();
    } finally

    {
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
    }
  }*/

  @Test
  public void createReminder() throws Exception {
    //ini error klo ga dibuat database nya
    //Reminder.createReminder("hello","rayandrew","a",timeStamp,timeStamp);
    //assertEquals(Reminder.selectReminder("rayandrew",1),null);
  }

  @Test
  public void createReminder1() throws Exception {
  }

  @Test
  public void selectReminder() throws Exception {
  }

  @Test
  public void getAllReminderFromUser() throws Exception {
  }

  @Test
  public void deleteReminder() throws Exception {
  }

  @Test
  public void updateReminder() throws Exception {
  }

  @Test
  public void getAttribute() throws Exception {
  }

}