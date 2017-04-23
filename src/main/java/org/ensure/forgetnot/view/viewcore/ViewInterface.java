package org.ensure.forgetnot.view.viewcore;

import java.awt.Component;

/**
 * Created by rufus on 4/12/2017.
 */
public interface ViewInterface {
  /**
   * Returns example title.
   * It might be used in examples tree or list.
   *
   * @return example title
   */
  public String getTitle();

  /**
   * Returns short example description.
   * It might be used in some additional information fields.
   *
   * @return short example description
   */
  public String getDescription();

  /**
   * Returns preview component for this example.
   * There might be any possible component here
   *
   * @return preview component
   */
  public Component getPreview();

  /**
   * Initialization of gui.
   */
  public void init();
}
