package org.ensure.forgetnot.core;

import org.ensure.forgetnot.controller.MainController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * class Launcher.
 * @author Ray
 */
public class Launcher {
  /**
   * Prosedur untuk membuat konfigurasi Main Controller untuk menjalankan swing.
   * */
  public void launch() {
    new AnnotationConfigApplicationContext(MainController.class);
  }
}
