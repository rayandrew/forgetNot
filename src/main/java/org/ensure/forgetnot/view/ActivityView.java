package org.ensure.forgetnot.view;

import com.alee.laf.text.WebTextField;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Button;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityView extends View {
  private Panel activityPanel;
  private JTable tab;
  private WebTextField textPane;
  private Button addActivityButton;
  private static String[] activityDescription;

  public ActivityView(int numOfActivities) {
    super("activityView");
    Action buttonClick = new Action();

    activityPanel = new Panel();
    textPane = new WebTextField(null, 10);
    addActivityButton = new Button("Add a Reminder");
    addActivityButton.addActionListener(buttonClick);
    activityDescription = new String[7];

    String[] columns = {"Activity", "Time"};
    Object[][] data = {{"Memasak", "20.20"}, {"Memasak", "20.20"}, {"Memasak", "20.20"}, {"Memasak", "20.20"}};

    tab = new JTable(data, columns);

    activityPanel.add(tab);
    activityPanel.add(textPane);
    activityPanel.add(addActivityButton);

    //TODO: buat array untuk simpan masukan pengguna
  }

  public void addWebContent(DefaultTableModel table) {
    tab.setModel(table);
  }

  //Action Listener For Button Click
  static class Action implements ActionListener {
    private static String[] tempInput = new String[7];

    @Override
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null, "Please input your activity",
          "Add a Reminder", JOptionPane.INFORMATION_MESSAGE);
      tempInput[2] = JOptionPane.showInputDialog(null, "Input the Reminder Title");
      tempInput[4] = JOptionPane.showInputDialog(null, "Description");
      tempInput[6] = JOptionPane.showInputDialog(null, "Remind Me At");
      JOptionPane.showMessageDialog(null, "Success", "Your reminder has been saved",
          JOptionPane.INFORMATION_MESSAGE);

      tempInput[0] = "test new username";
      String timeStamp = LocalDateTime.now().format(
          DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
      );
      tempInput[5] = timeStamp;
      tempInput[6] = timeStamp;
    }

    public static String[] returnInput() {
      return tempInput;
    }
  }

  public String[] returnInput() {
    return Action.returnInput();
  }

  @Override
  public Component init() {
    //WebTable activityTable = new WebTable();
    return activityPanel;
  }
}
