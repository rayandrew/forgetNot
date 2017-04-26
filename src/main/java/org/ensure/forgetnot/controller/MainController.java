package org.ensure.forgetnot.controller;

import java.awt.Component;

import org.ensure.forgetnot.view.MainView;
import org.springframework.context.annotation.Bean;

/**
 * class MainController.
 *
 * @author rayandrew
 */
public class MainController extends Controller {
  /**
   * Constructor.
   */
  public MainController() {
    view = new MainView("forgetNot by Ensure");
  }

  @Bean(name = "mainView")
  public Component init() {
    return view.init();
  }
}
