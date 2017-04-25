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

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityView extends View {
  private Panel activityPanel;
  private JTable tab;
  private WebTextField textPane;
  private Button addActivityButton;

  public ActivityView(int numOfActivities) {
    super("activityView");
    //System.out.println(numOfActivities);

    activityPanel = new Panel();
    textPane = new WebTextField(null,10);
    addActivityButton = new Button("Add a Reminder");
    addActivityButton.addActionListener(new Action());

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
    @Override
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null, "Please input your activity",
          "Add a Reminder", JOptionPane.INFORMATION_MESSAGE);
      String input1 = JOptionPane.showInputDialog(null,"Input the Reminder Title");
    }
  }

  @Override
  public Component init() {
    //WebTable activityTable = new WebTable();
    return activityPanel;
  }
}
