package org.ensure.forgetnot.controller;

import java.awt.Component;

import org.ensure.forgetnot.core.Controller;
import org.ensure.forgetnot.view.MainView;
import org.springframework.context.annotation.Bean;

/**
 * Created by rufus on 4/23/2017.
 */
public class MainController extends Controller {
  public MainController() {
    view = new MainView("forgetNot by Ensure");
  }

  @Bean(name = "mainView")
  public Component init() {
    return view.init();
  }
}
