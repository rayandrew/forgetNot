package org.ensure.forgetnot.view;

import com.alee.laf.button.WebButton;
import org.ensure.forgetnot.utility.Pair;

import java.awt.Button;
import java.awt.Component;
import java.util.List;

/**
 * Created by rufus on 4/23/2017.
 */
public class LoginView extends View {
  public LoginView(String componentName) {
    super(componentName);
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
    return new WebButton("Login");
  }
}
