package org.ensure.forgetnot.view;

import java.util.ArrayList;
import java.util.List;

import org.ensure.forgetnot.utility.Pair;
import org.ensure.forgetnot.view.viewcore.ViewInterface;



/**
 * Created by rufus on 4/23/2017.
 */
public abstract class View implements ViewInterface {
  protected List<Pair<String, Object>> dataInput = new ArrayList<Pair<String, Object>>();
  protected String componentName = new String();
  protected String componentDescription = new String();

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
}
