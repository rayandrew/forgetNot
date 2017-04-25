package org.ensure.forgetnot.view;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import org.ensure.forgetnot.utility.Pair;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by rufus on 4/23/2017.
 */
public class LoginView extends View {
  private Panel allPanel;
  private WebLabel usernameLabel = new WebLabel("Username");
  private WebLabel passwordLabel = new WebLabel("Password");
  private WebButton loginButton = new WebButton("Login");
  private WebButton registerButton = new WebButton("Register");
  private WebTextField usernameField = new WebTextField(15);
  private WebPasswordField passwordField = new WebPasswordField(15);

  public LoginView(String componentName) {
    super(componentName);
    allPanel = new Panel();
    Panel username = new Panel();
    Panel password = new Panel();
    Panel button = new Panel();
    username.add(usernameLabel);
    username.add(usernameField);
    password.add(passwordLabel);
    password.add(passwordField);
    ActionListener buttonclick = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        RegisterView reg = new RegisterView("apakek");
      }
    };
    registerButton.addActionListener(buttonclick);
    button.add(loginButton);
    button.add(registerButton);
    allPanel.add(username);
    allPanel.add(password);
    allPanel.add(button);
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
    return allPanel;
  }
}
