package org.ensure.forgetnot.core;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.ensure.forgetnot.utility.Pair;


/**
 * Created by rufus on 4/23/2017.
 */
public abstract class View {
  protected List<Pair<String, Object>> dataInput = new ArrayList<Pair<String, Object>>();
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
