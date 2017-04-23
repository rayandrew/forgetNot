package org.ensure.forgetnot.controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.ensure.forgetnot.view.MainView;
import org.springframework.context.annotation.Bean;

/**
 * Created by rufus on 4/12/2017.
 */
public class Controller {
  @Bean(name = "mainView")
  protected Component init() {
    return new MainView("forgetNot by Ensure").getPreview();
  }

  @Bean
  private List<Component> panelComponent() {
    List<Component> panelComponent = new ArrayList<Component>();
    return panelComponent;
  }
}
