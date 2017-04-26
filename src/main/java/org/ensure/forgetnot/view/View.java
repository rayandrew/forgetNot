package org.ensure.forgetnot.view;

import org.ensure.forgetnot.utility.Pair;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;


/**
 * Kelas Abstract View.
 * @author Ray
 */
public abstract class View {
  protected List<Pair<String, Object>> dataInput = new ArrayList<>();
  protected String componentName;
  protected String componentDescription;

  /**
   * View sebagai Konstruktor.
   *
   * @param componentName menerima nama komponen
   */
  public View(String componentName) {
    this.componentName = componentName;
  }

  /**
   * View sebagai Konstruktor.
   *
   * @param componentName        nama komponen
   * @param componentDescription deskripsi komponen
   */
  public View(String componentName, String componentDescription) {
    this.componentName = componentName;
    this.componentDescription = componentDescription;
  }

  /**
   * View konstruktor.
   *
   * @param componentName        nama komponen
   * @param componentDescription deskripsi komponen
   * @param dataInput            List of pair yang dapat menerima sebuah pair Key - Value
   */
  public View(String componentName,
              String componentDescription,
              List<Pair<String, Object>> dataInput
  ) {
    this.componentName = componentName;
    this.componentDescription = componentDescription;
    this.dataInput = dataInput;
  }

  /**
   * View Konstruktor.
   *
   * @param dataInput menerima list of pair Key - Value
   */
  public View(List<Pair<String, Object>> dataInput) {
    this.dataInput = dataInput;
  }

  /**
   * getData untuk mengembalikan dataInput yang dimiliki.
   *
   * @return Mengembalikan list of pair key - value
   */
  protected List<Pair<String, Object>> getData() {
    return dataInput;
  }

  /**
   * setData untuk mengeset dataInput dengan input dari user.
   *
   * @param dataInput objek yang ingin dimasukan
   */
  protected void setData(List<Pair<String, Object>> dataInput) {
    this.dataInput = dataInput;
  }

  /**
   * getTitle mengembalikan title.
   *
   * @return mengembalikan judul view
   */
  public String getTitle() {
    return this.componentName;
  }

  /**
   * getDescription untuk mengembalikan Deskripsi komponen.
   *
   * @return mengembalikan deskripsi
   */
  public String getDescription() {
    return this.componentDescription;
  }

  /**
   * @return Mengembalikan suatu komponen Swing yang akan ditampilkan.
   */
  public abstract Component init();
}
