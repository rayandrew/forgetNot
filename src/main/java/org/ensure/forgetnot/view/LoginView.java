package org.ensure.forgetnot.view;

import com.alee.extended.layout.TableLayout;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import org.ensure.forgetnot.controller.LoginController;
import org.ensure.forgetnot.core.Launcher;
import org.ensure.forgetnot.utility.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Kelas Login View.
 *
 * @author Ray
 */
public class LoginView extends View {
  static final Logger logger = LoggerFactory.getLogger(LoginView.class);
  private WebFrame frameLogin = new WebFrame("Login forgetNot");
  private WebPanel content;
  private WebLabel usernameLabel = new WebLabel("Username", WebLabel.TRAILING);
  private WebLabel passwordLabel = new WebLabel("Password", WebLabel.TRAILING);
  private WebButton loginButton = new WebButton("Login");
  private WebButton registerButton = new WebButton("Register");
  private WebTextField usernameField = new WebTextField(15);
  private WebPasswordField passwordField = new WebPasswordField(15);

  /**
   * Konstruktor.
   *
   * @param componentName Nama komponen
   */
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
    frameLogin.setVisible(true);
    ActionListener loginVerify = e -> {
      if (LoginController.verifyLogin(
        usernameField.getText(),
        String.valueOf(passwordField.getPassword())
      )) {
        frameLogin.setVisible(false);
        frameLogin.dispose();
        Launcher.launch();
      } else {
        WebOptionPane.showMessageDialog(null,
          "Failed to login!",
          "Failed Message",
          WebOptionPane.ERROR_MESSAGE);
      }
    };

    ActionListener buttonclick = e -> {
      RegisterView reg = new RegisterView("Register");
    };
    registerButton.addActionListener(buttonclick);
    loginButton.addActionListener(loginVerify);
    content.add(loginButton, "1,2");
    content.add(registerButton, "0,2");
    frameLogin.add(content);
  }

  /**
   * Konstruktor dengan parameter.
   *
   * @param componentName        nama komponen
   * @param componentDescription deskripsi komponen
   */
  public LoginView(String componentName, String componentDescription) {
    super(componentName, componentDescription);
  }

  /**
   * Konstuktor dengan parameter.
   *
   * @param componentName        nama komponen
   * @param componentDescription deskripsi komponen
   * @param dataInput            list of pair
   */
  public LoginView(String componentName,
                   String componentDescription,
                   List<Pair<String, Object>> dataInput
  ) {
    super(componentName, componentDescription, dataInput);
  }

  /**
   * Konstuktor dengan parameter.
   *
   * @param dataInput list of pair
   */
  public LoginView(List<Pair<String, Object>> dataInput) {
    super(dataInput);
  }

  @Override
  public Component init() {
    frameLogin.setSize(new Dimension(200, 200));
    frameLogin.pack();
    frameLogin.setLocationRelativeTo(null);
    frameLogin.setDefaultCloseOperation(WebFrame.EXIT_ON_CLOSE);
    return frameLogin;
  }
}
