package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.view.MainView;
import org.springframework.context.annotation.Bean;

import java.awt.*;

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
