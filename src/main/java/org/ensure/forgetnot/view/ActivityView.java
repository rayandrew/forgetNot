package org.ensure.forgetnot.view;

import com.alee.extended.layout.TableLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebTextField;
import org.ensure.forgetnot.controller.ActivityController;
import org.ensure.forgetnot.model.Reminder;
import org.ensure.forgetnot.utility.Pair;

import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Button;
import java.awt.Component;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rufus on 4/23/2017.
 */
public class ActivityView extends View {
  private Panel activityPanel;
  private JTable tab;
  private Button addActivityButton;

  public ActivityView(Object[][] dataInput) {
    super("activityViewer");
    activityPanel = new Panel();
    addActivityButton = new Button("Add a Reminder");
    addActivityButton.addActionListener(new Dialog());

    String[] columns = {"Activity","Description","Time"};
    tab = new JTable(dataInput, columns);

    activityPanel.add(tab);
    activityPanel.add(addActivityButton);
    activityPanel.add(new GroupPanel(false,tab,addActivityButton));

    //TODO: buat array untuk simpan masukan pengguna
  }

  public void addWebContent(DefaultTableModel table) {
    tab.setModel(table);
  }

  private class Dialog extends WebDialog implements ActionListener {
    private WebTextField title;
    private WebTextField contentReminder;
    private WebSpinner dueDate;
    private WebButton confirm;
    private String[] activityDescription;

    public Dialog() {
      super();
      setSize(500, 200);
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

  @Override
  public Component init() {
    return activityPanel;
  }
}
