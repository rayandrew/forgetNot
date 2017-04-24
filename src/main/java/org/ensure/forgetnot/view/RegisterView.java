package org.ensure.forgetnot.view;

import com.alee.laf.button.WebButton;
import org.ensure.forgetnot.utility.Pair;

import java.awt.Button;
import java.awt.Component;
import java.util.List;

/**
 * Created by rufus on 4/24/2017.
 */
public class RegisterView extends View {
  public RegisterView(String componentName) {
    super(componentName);
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

  @Override
  public Component init() {
    return new WebButton("Register");
  }
}
