package org.ensure.forgetnot.model;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public ReminderTest() {

}

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