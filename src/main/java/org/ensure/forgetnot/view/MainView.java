package org.ensure.forgetnot.view;

import com.alee.laf.rootpane.WebFrame;
import org.ensure.forgetnot.utility.Pair;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

/**
 * Kelas MainView.
 * @author Ray
 */
public class MainView extends View {
  /**
   * Konstruktor.
   *
   * @param componentName Nama komponen
   */
  public MainView(String componentName) {
    super(componentName);
  }

  /**
   * Konstruktor dengan parameter.
   *
   * @param componentName        nama komponen
   * @param componentDescription deskripsi komponen
   */
  public MainView(String componentName, String componentDescription) {
    super(componentName, componentDescription);
  }

  /**
   * Konstruktor dengan parameter.
   *
   * @param componentName        nama komponen
   * @param componentDescription deskripsi komponen
   * @param dataInput            list of pair Key - Value
   */
  public MainView(String componentName,
                  String componentDescription,
                  List<Pair<String, Object>> dataInput
  ) {
    super(componentName, componentDescription, dataInput);
  }

  /**
   * Konstruktor dengan parameter.
   *
   * @param dataInput List of Pair
   */
  public MainView(List<Pair<String, Object>> dataInput) {
    super(dataInput);
  }

  @Override
  public Component init() {
    WebFrame mainWindow = new WebFrame(componentName);
    mainWindow.setSize(new Dimension(600, 400));
    ContainerView container = new ContainerView();
    container.setAxis(1);
    container.init();
    mainWindow.setContentPane(container);
    mainWindow.setState(WebFrame.NORMAL);
    mainWindow.pack();
    mainWindow.setLocationRelativeTo(null);
    mainWindow.setDefaultCloseOperation(WebFrame.EXIT_ON_CLOSE);
    mainWindow.setVisible(true);
    return mainWindow;
  }
}
