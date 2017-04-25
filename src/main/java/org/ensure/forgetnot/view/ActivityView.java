package org.ensure.forgetnot.view;

import com.alee.laf.text.WebTextField;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Panel;

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityView extends View {
  private Panel activityPanel;
  private JTable tab;
  private WebTextField textPane;

  public ActivityView(int numOfActivities) {
    super("activityView");
    //System.out.println(numOfActivities);

    activityPanel = new Panel();
    textPane = new WebTextField();


    String[] columns = {"Activity", "Time"};
    Object[][] data = {{"Memasak", "20.20"}, {"Memasak", "20.20"}, {"Memasak", "20.20"}, {"Memasak", "20.20"}};

    tab = new JTable(data, columns);

    activityPanel.add(tab);
    activityPanel.add(textPane);
  }

  public void addWebContent(DefaultTableModel table) {
    tab.setModel(table);
  }

  public void getValueInput() {

  }

  @Override
  public Component init() {
    //WebTable activityTable = new WebTable();
    return activityPanel;
  }
}
