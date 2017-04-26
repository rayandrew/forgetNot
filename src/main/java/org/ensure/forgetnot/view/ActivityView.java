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
import org.ensure.forgetnot.core.Config;
import org.ensure.forgetnot.core.Database;
import org.ensure.forgetnot.model.Reminder;

import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
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
 * Kelas ActivityView.
 *
 * @author Aldrich
 */
public class ActivityView extends View {
  private Panel activityPanel;
  private WebTable tab;
  private WebButton addActivityButton;
  private WebButton deleteButton;
  private WebButton updateButton;
  private String[] columns = {"ID", "Activity", "Description", "Time"};

  /**
   * Konstruktor.
   */
  public ActivityView() {
    super("activityViewer");
    activityPanel = new Panel();
    addActivityButton = new WebButton("Add a Reminder");
    addActivityButton.addActionListener(new Dialog());
    deleteButton = new WebButton("Delete a Reminder");
    deleteButton.addActionListener(e -> {
      Integer temp = Integer.parseInt(WebOptionPane.showInputDialog(
        null,
        "Insert reminder ID to be deleted",
        "Delete Reminder",
        WebOptionPane.WARNING_MESSAGE));
      Database.connect();
      boolean status = Reminder.deleteReminder(Config.getLoginUser(), temp);
      Database.close();
      if (status) {
        DefaultTableModel table = new DefaultTableModel(
          ActivityController.refresh(),
          columns
        );
        tab.setModel(table);
        SwingUtilities.getRootPane(activityPanel).revalidate();
        WebOptionPane.showMessageDialog(
          null,
          "Success deleted reminder id" + temp,
          "Delete Reminder",
          WebOptionPane.INFORMATION_MESSAGE);
      } else {
        WebOptionPane.showMessageDialog(
          null,
          "Failed to delete reminder id" + temp,
          "Delete Reminder",
          WebOptionPane.WARNING_MESSAGE);
      }
    });
    updateButton = new WebButton("Modify a Reminder");
    updateButton.addActionListener(e -> {
      Integer temp = Integer.parseInt(WebOptionPane.showInputDialog(
        null,
        "Please input your reminder ID",
        "Modify Reminder",
        WebOptionPane.WARNING_MESSAGE)
      );
      new UpdateDialog(temp);
    });

    tab = new WebTable(ActivityController.getTabelEntry(), columns);
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

    /**
     * Konstruktor.
     */
    Dialog() {
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
      ActionListener saveToDatabase = (ActionEvent e) -> {
        activityDescription[0] = Config.getLoginUser();
        activityDescription[2] = title.getText();
        activityDescription[4] = contentReminder.getText();
        Date temp = (Date) dueDate.getValue();
        LocalDateTime tempDate = LocalDateTime.ofInstant(
          temp.toInstant(),
          ZoneId.systemDefault()
        );
        activityDescription[6] = tempDate.format(
          DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        if (ActivityController.addActivity(activityDescription)) {
          WebOptionPane.showMessageDialog(null,
            "Your reminder has been saved!",
            "Success",
            WebOptionPane.INFORMATION_MESSAGE
          );
        } else {
          WebOptionPane.showMessageDialog(null,
            "Your reminder cannot be saved",
            "Failed",
            WebOptionPane.ERROR_MESSAGE
          );
        }
        DefaultTableModel table = new DefaultTableModel(
          ActivityController.refresh(),
          columns
        );
        tab.setModel(table);
        SwingUtilities.getRootPane(activityPanel).revalidate();
        setVisible(false);
        dispose();
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
      setVisible(true);
    }
  }

  private class UpdateDialog extends WebDialog {
    private WebTextField title;
    private WebTextField contentReminder;
    private WebSpinner dueDate;
    private WebButton confirm;
    private String[] activityDescription;

    /**
     * Konstruktor.
     */
    UpdateDialog(int id) {
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
      Database.connect();
      Reminder r = Reminder.selectReminder(Config.getLoginUser(), id);
      if (r != null) {
        title = new WebTextField(r.getString("reminder_title"), 15);
        contentReminder = new WebTextField(r.getString("content"), 15);
        dueDate = new WebSpinner();
        SpinnerDateModel spinner = new SpinnerDateModel();
        spinner.setCalendarField(Calendar.YEAR);
        dueDate.setModel(spinner);
        dueDate.setValue(new Date());
      } else {
        WebOptionPane.showMessageDialog(null,
          "Reminder not found",
          "Failed",
          WebOptionPane.ERROR_MESSAGE
        );
        setVisible(false);
        dispose();
      }
      Database.close();
      confirm = new WebButton("Set Reminder");

      //add ActionListener for confirm Button
      ActionListener saveToDatabase = e -> {
        activityDescription[0] = Config.getLoginUser(); //nanti diisi dengan user
        activityDescription[2] = title.getText();
        activityDescription[4] = contentReminder.getText();
        Date temp = (Date) dueDate.getValue();
        LocalDateTime tempDate = LocalDateTime.ofInstant(temp.toInstant(), ZoneId.systemDefault());
        activityDescription[6] = tempDate.format(
          DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        Database.connect();
        boolean status = Reminder.updateReminder(
          activityDescription[0],
          id,
          "reminder_title",
          activityDescription[2]
        );
        status = status && Reminder.updateReminder(
          activityDescription[0], id, "content", activityDescription[4]
        );

        status = status && Reminder.updateReminder(
          activityDescription[0],
          id,
          "due_time",
          activityDescription[6]
        );
        Database.close();
        if (status) {
          DefaultTableModel table = new DefaultTableModel(
            ActivityController.refresh(),
            columns
          );
          tab.setModel(table);
          SwingUtilities.getRootPane(activityPanel).revalidate();

          WebOptionPane.showMessageDialog(null,
            "Your reminder has been updated!",
            "Success",
            WebOptionPane.INFORMATION_MESSAGE);
          setVisible(false);
          dispose();
        } else {
          WebOptionPane.showMessageDialog(null,
            "Your reminder cannot be updated",
            "Failed",
            WebOptionPane.ERROR_MESSAGE
          );
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
