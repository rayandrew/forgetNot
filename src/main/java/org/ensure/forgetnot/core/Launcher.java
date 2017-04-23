package org.ensure.forgetnot.core;

import org.ensure.forgetnot.controller.MainController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by rufus on 4/15/2017.
 */
public class Launcher {
  public void launch() {
    // String[] contextPaths = new String[]{
    //"org/ensure/forgetnot/app-context.xml"
    //};
    //new ClassPathXmlApplicationContext(contextPaths);
    new AnnotationConfigApplicationContext(MainController.class);
  }
}
