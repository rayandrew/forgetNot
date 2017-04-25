package org.ensure.forgetnot.view;

import com.alee.extended.layout.TableLayout;
import com.alee.extended.panel.CenterPanel;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.utils.SwingUtils;
import org.ensure.forgetnot.utility.Pair;

import javax.swing.JSeparator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by rufus on 4/24/2017.
 */
public class RegisterView extends View {
  Dialog dialog;
  public RegisterView(String componentName) {
    super(componentName);
    Dialog dialog = new Dialog();
    dialog.pack();
    dialog.setVisible(true);
  }

  public RegisterView(String componentName, String componentDescription) {
    super(componentName, componentDescription);
  }

  public RegisterView(String componentName,
                      String componentDescription,
                      List<Pair<String, Object>> dataInput
  ) {
    super(componentName, componentDescription, dataInput);
  }

  public RegisterView(List<Pair<String, Object>> dataInput) {
    super(dataInput);
  }

  private class Dialog extends WebDialog {
    public Dialog() {
      super();
      setIconImages(WebLookAndFeel.getImages());
      setDefaultCloseOperation(WebDialog.DISPOSE_ON_CLOSE);
      setResizable(false);
      setModal(true);
      TableLayout layout = new TableLayout(new double[][]{{TableLayout.PREFERRED, TableLayout.FILL},
          {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}});
      layout.setHGap(5);
      layout.setVGap(5);
      WebPanel content = new WebPanel(layout);
      content.setMargin(15, 30, 15, 30);
      content.setOpaque(false);
      content.add(new WebLabel("Username", WebLabel.TRAILING), "0,0");
      content.add(new WebTextField(15), "1,0");

      content.add(new WebLabel("Password", WebLabel.TRAILING), "0,1");
      content.add(new WebPasswordField(15), "1,1");
      WebButton login = new WebButton("Register");
      WebButton cancel = new WebButton("Cancel");
      ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          setVisible(false);
        }
      };
      login.addActionListener(listener);
      cancel.addActionListener(listener);
      content.add(new CenterPanel(new GroupPanel(5, login, cancel)), "0,2,1,2");
      SwingUtils.equalizeComponentsWidths(login, cancel);
      add(content);
      HotkeyManager.registerHotkey(this, login, Hotkey.ESCAPE);
      HotkeyManager.registerHotkey(this, login, Hotkey.ENTER);
    }
  }

  @Override
  public Component init() {
    dialog.setLocationRelativeTo(null);
    return dialog;
  }
}
