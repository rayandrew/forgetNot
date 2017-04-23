package org.ensure.forgetnot.core;

import org.ensure.forgetnot.controller.Controller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by rufus on 4/15/2017.
 */
public class Launcher {
  public void launch() {
    // String[] contextPaths = new String[]{
    //"org/ensure/forgetnot/app-context.xml"
    //};
    //new ClassPathXmlApplicationContext(contextPaths);
    new AnnotationConfigApplicationContext(Controller.class);
  }
}
