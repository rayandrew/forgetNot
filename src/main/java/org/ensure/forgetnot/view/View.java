package org.ensure.forgetnot.view;

import org.ensure.forgetnot.utility.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rufus on 4/23/2017.
 */
public abstract class View {
  protected List<Pair<String, Object>> dataInput = new ArrayList<>();
  protected String componentName;
  protected String componentDescription;

  public View(String componentName) {
    this.componentName = componentName;
  }

  public View(String componentName, String componentDescription) {
    this.componentName = componentName;
    this.componentDescription = componentDescription;
  }

  public View(String componentName,
              String componentDescription,
              List<Pair<String, Object>> dataInput
  ) {
    this.componentName = componentName;
    this.componentDescription = componentDescription;
    this.dataInput = dataInput;
  }

  public View(List<Pair<String, Object>> dataInput) {
    this.dataInput = dataInput;
  }

  protected void setData(List<Pair<String, Object>> dataInput) {
    this.dataInput = dataInput;
  }

  protected List<Pair<String, Object>> getData() {
    return dataInput;
  }

  public String getTitle() {
    return this.componentName;
  }

  public String getDescription() {
    return this.componentDescription;
  }

  public abstract Component init();
}
