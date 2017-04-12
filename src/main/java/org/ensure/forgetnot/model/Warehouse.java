package org.ensure.forgetnot.model;

import org.javalite.activejdbc.Model;


/**
 * Created by rufus on 4/12/2017.
 */
public class Warehouse extends Model {
  public Warehouse(String name) {
    set("name", name);
  }
}
