package org.ensure.forgetnot.view;

import com.alee.extended.layout.TableLayout;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import org.ensure.forgetnot.utility.Pair;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by rufus on 4/23/2017.
 */
public class LoginView extends View {
  private WebPanel content;
  private WebLabel usernameLabel = new WebLabel("Username", WebLabel.TRAILING);
  private WebLabel passwordLabel = new WebLabel("Password", WebLabel.TRAILING);
  private WebButton loginButton = new WebButton("Login");
  private WebButton registerButton = new WebButton("Register");
  private WebTextField usernameField = new WebTextField(15);
  private WebPasswordField passwordField = new WebPasswordField(15);

  public LoginView(String componentName) {
    super(componentName);
    TableLayout layout = new TableLayout(new double[][]{{TableLayout.PREFERRED, TableLayout.FILL},
      {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}});
    layout.setHGap(5);
    layout.setVGap(5);
    content = new WebPanel(layout);
    content.add(usernameLabel, "0,0");
    usernameField.setInputPrompt("Enter Username..");
    usernameField.setInputPromptFont(usernameField.getFont().deriveFont(Font.ITALIC));
    content.add(usernameField, "1,0");
    content.add(passwordLabel, "0,1");
    passwordField.setInputPrompt("Enter Password..");
    passwordField.setInputPromptFont(passwordField.getFont().deriveFont(Font.ITALIC));
    content.add(passwordField, "1,1");
    ActionListener buttonclick = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        RegisterView reg = new RegisterView("Register");
      }
    };
    registerButton.addActionListener(buttonclick);
    content.add(loginButton, "1,2");
    content.add(registerButton, "0,2");
  }

  public LoginView(String componentName, String componentDescription) {
    super(componentName, componentDescription);
  }

  public LoginView(String componentName,
                   String componentDescription,
                   List<Pair<String, Object>> dataInput
  ) {
    super(componentName, componentDescription, dataInput);
  }

  public LoginView(List<Pair<String, Object>> dataInput) {
    super(dataInput);
  }

  @Override
  public Component init() {
    return content;
  }
}
