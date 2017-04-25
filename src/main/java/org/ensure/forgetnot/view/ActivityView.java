package org.ensure.forgetnot.view;

import com.alee.extended.layout.TableLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebTextField;
import org.ensure.forgetnot.controller.ActivityController;
import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.Reminder;

import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityView extends View {
  private Panel activityPanel;
  private WebTable tab;
  private WebButton addActivityButton;
  private WebButton deleteButton;
  private WebButton updateButton;

  public ActivityView(Object[][] dataInput) {
    super("activityViewer");
    activityPanel = new Panel();
    addActivityButton = new WebButton("Add a Reminder");
    addActivityButton.addActionListener(new Dialog());
    deleteButton = new WebButton("Delete a Reminder");
    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Integer temp = Integer.parseInt(WebOptionPane.showInputDialog(
            null,
            "Insert reminder ID to be deleted",
            "Delete Reminder",
            WebOptionPane.WARNING_MESSAGE));
        //TODO: usernamenya jgn lupa
        if(temp != null){
          Database.connect();
          Reminder.deleteReminder("rayandrew", temp);
          Database.close();
        }
      }
    });
    updateButton = new WebButton("Modify a Reminder");
    updateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Integer temp = Integer.parseInt(WebOptionPane.showInputDialog(
            null,
            "Please input your reminder ID",
            "Modify Reminder",
            WebOptionPane.WARNING_MESSAGE)
        );
        new UpdateDialog(temp);
      }
    });

    String[] columns = {"ID", "Activity", "Description", "Time"};
    /*DefaultTableModel tableModel = new DefaultTableModel(columns, dataInput.length) {
      public boolean isCellEditable(int row, int column) {
        return false;//This causes all cells to be not editable
      }
    };*/

    tab = new WebTable(dataInput, columns);
    //tab.setModel(tableModel);
    tab.getColumnModel().getColumn(0).setPreferredWidth(20);
    tab.getColumnModel().getColumn(1).setPreferredWidth(100);
    tab.getColumnModel().getColumn(2).setPreferredWidth(400);
    tab.getColumnModel().getColumn(3).setPreferredWidth(150);

    activityPanel.add(tab);
    Panel buttons = new Panel();
    buttons.add(addActivityButton);
    buttons.add(deleteButton);
    buttons.add(updateButton);
    activityPanel.add(new GroupPanel(false, tab, buttons));
  }

  public void addWebContent(DefaultTableModel table) {
    tab.setModel(table);
  }

  @Override
  public Component init() {
    return activityPanel;
  }

  private class Dialog extends WebDialog implements ActionListener {
    private WebTextField title;
    private WebTextField contentReminder;
    private WebSpinner dueDate;
    private WebButton confirm;
    private String[] activityDescription;

    public Dialog() {
      super();
      setSize(500, 175);
      setTitle("Add New Reminder");
      activityDescription = new String[7];

      TableLayout layout = new TableLayout(new double[][]{{TableLayout.PREFERRED, TableLayout.FILL},
          {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}});
      layout.setHGap(2);
      layout.setVGap(2);
      WebPanel content = new WebPanel(layout);
      content.setMargin(10, 10, 10, 10);
      content.setOpaque(false);

      //fields
      title = new WebTextField("Enter title", 15);
      contentReminder = new WebTextField("Enter contentReminder", 15);
      dueDate = new WebSpinner();
      SpinnerDateModel spinner = new SpinnerDateModel();
      spinner.setCalendarField(Calendar.YEAR);
      dueDate.setModel(spinner);
      dueDate.setValue(new Date());
      confirm = new WebButton("Set Reminder");

      //add ActionListener for confirm Button
      ActionListener saveToDatabase = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Date temp = (Date) dueDate.getValue();
          LocalDateTime tempDate = LocalDateTime.ofInstant(temp.toInstant(), ZoneId.systemDefault());
          activityDescription[0] = "rayandrew"; //nanti diisi dengan user
          activityDescription[2] = title.getText();
          activityDescription[4] = contentReminder.getText();
          activityDescription[6] = tempDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
          //System.out.println("ini waktu format dari input : " + activityDescription[6]);
          ActivityController.addActivity(activityDescription);
          WebOptionPane.showMessageDialog(null,
              "Your reminder has been saved!", "Success", WebOptionPane.INFORMATION_MESSAGE);
        }
      };
      confirm.addActionListener(saveToDatabase);

      content.add(new WebLabel("Reminder Title", WebLabel.TRAILING), "0,0");
      content.add(title, "1,0");

      content.add(new WebLabel("Content", WebLabel.TRAILING), "0,1");
      content.add(contentReminder, "1,1");

      content.add(new WebLabel("Choose Due Date", WebLabel.TRAILING), "0,2");
      content.add(dueDate, "1,2");

      add(content);
      add(confirm);
      add(new GroupPanel(false, content, confirm));
      center();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      //String[] columns = {"Activity","Description","Time"};
      //tab = new JTable(ActivityController.refresh(),columns);
      setVisible(true);
    }
  }

  private class UpdateDialog extends WebDialog {
    private WebTextField title;
    private WebTextField contentReminder;
    private WebSpinner dueDate;
    private WebButton confirm;
    private String[] activityDescription;

    public UpdateDialog(int id) {
      super();
      setSize(500, 175);
      setTitle("Modify Reminder");
      activityDescription = new String[7];

      TableLayout layout = new TableLayout(new double[][]{{TableLayout.PREFERRED, TableLayout.FILL},
          {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}});
      layout.setHGap(2);
      layout.setVGap(2);
      WebPanel content = new WebPanel(layout);
      content.setMargin(10, 10, 10, 10);
      content.setOpaque(false);

      //fields
      title = new WebTextField("Enter title", 15);
      contentReminder = new WebTextField("Enter contentReminder", 15);
      dueDate = new WebSpinner();
      SpinnerDateModel spinner = new SpinnerDateModel();
      spinner.setCalendarField(Calendar.YEAR);
      dueDate.setModel(spinner);
      dueDate.setValue(new Date());
      confirm = new WebButton("Set Reminder");

      //add ActionListener for confirm Button
      ActionListener saveToDatabase = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Date temp = (Date) dueDate.getValue();
          LocalDateTime tempDate = LocalDateTime.ofInstant(temp.toInstant(), ZoneId.systemDefault());
          activityDescription[0] = "rayandrew"; //nanti diisi dengan user
          activityDescription[2] = title.getText();
          activityDescription[4] = contentReminder.getText();
          activityDescription[6] = tempDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

          Database.connect();
          Reminder.updateReminder(activityDescription[0], id, "reminder_title", activityDescription[2]);
          Reminder.updateReminder(activityDescription[0], id, "content", activityDescription[4]);
          Reminder.updateReminder(activityDescription[0], id, "due_time", activityDescription[6]);
          Database.close();
          WebOptionPane.showMessageDialog(null,
              "Your reminder has been updated!", "Success", WebOptionPane.INFORMATION_MESSAGE);
        }
      };
      confirm.addActionListener(saveToDatabase);

      content.add(new WebLabel("Reminder Title", WebLabel.TRAILING), "0,0");
      content.add(title, "1,0");

      content.add(new WebLabel("Content", WebLabel.TRAILING), "0,1");
      content.add(contentReminder, "1,1");

      content.add(new WebLabel("Choose Due Date", WebLabel.TRAILING), "0,2");
      content.add(dueDate, "1,2");

      add(content);
      add(confirm);
      add(new GroupPanel(false, content, confirm));
      center();
      setVisible(true);
    }
  }
}
