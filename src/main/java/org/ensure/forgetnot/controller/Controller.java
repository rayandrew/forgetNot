package org.ensure.forgetnot.controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.ensure.forgetnot.utility.Pair;
import org.ensure.forgetnot.view.View;

/**
 * Class Controller.
 *
 * @author rayandrew
 */
public abstract class Controller {
  protected List<Pair<String, Object>> data = new ArrayList<>();
  protected View view;
  protected boolean show = true;

  /**
   * Boolean untuk view ditunjukkan atau tidak.
   *
   * @return boolean Boolean yang mengindikasikan view ditunjukkan atau tidak
   */
  public boolean isShow() {
    return show;
  }

  /**
   * Method Component.
   *
   * @return Component swing yang akan ditampilkan ke layar
   */
  public abstract Component init();
}
