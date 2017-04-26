package org.ensure.forgetnot.view;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.time.ClockType;
import com.alee.extended.time.WebClock;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.managers.notification.NotificationIcon;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotification;

import java.awt.Component;

/**
 * Kelas Reminder View.
 *
 * @author Girvandi
 */
public class ReminderView extends View {
  private int idReminder;
  private String username;

  /**
   * Konstruktor tanpa parameter.
   *
   * @param loginUser return string
   * @param temp return integer
   */
  public ReminderView(String loginUser, int temp) {
    super("Reminder");
    username = loginUser;
    idReminder = temp;
  }

  @Override
  public Component init() {
    final WebButton notification2 = new WebButton("Limited duration notification");
    final WebNotification notificationPopup = new WebNotification();
    notificationPopup.setIcon(NotificationIcon.clock);
    notificationPopup.setDisplayTime(8000);

    notificationPopup.add(new WebLabel("Notification"));
    notificationPopup.add(new WebLabel("Test"));


    final WebClock clock = new WebClock();
    clock.setClockType(ClockType.timer);
    clock.setTimeLeft(8000);
    clock.setTimePattern("'This notification will close in' ss 'seconds'");
    WebPanel textingReminder = new WebPanel("WEW");
    GroupPanel gp = new GroupPanel();
    gp.add(textingReminder);
    gp.add(clock);
    notificationPopup.setContent(gp);

    NotificationManager.showNotification(notificationPopup);
    clock.start();


    notification2.setVisible(false);
    return notification2;
  }
}
