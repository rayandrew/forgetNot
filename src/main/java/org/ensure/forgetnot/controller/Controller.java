package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.utility.Pair;
import org.ensure.forgetnot.view.View;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayandrew
 * Class Controller
 */
public abstract class Controller {
  protected List<Pair<String, Object>> data = new ArrayList<>();
  protected View view;
  protected boolean show = true;

  /**
   * @return boolean Boolean yang mengindikasikan view ditunjukkan atau tidak
   */
  public boolean isShow() {
    return show;
  }

  /**
   *
   * @return Component swing yang akan ditampilkan ke layar
   */
  public abstract Component init();
}
