package org.ensure.forgetnot.controller;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;

import java.awt.Component;
import java.time.LocalDateTime;

/**
 * Created by agath on 24/04/2017.
 */
public class ClockController extends Controller implements Runnable {
  private int hh, mm, ss;
  private Thread t;
  private String threadname;
  public static ClockController clock = new ClockController("ClockController");

  public ClockController() {
    LocalDateTime now = LocalDateTime.now();
    hh = now.getHour();
    mm = now.getMinute();
    ss = now.getSecond();
    threadname = "ClockControler";
    System.out.println("Creating " + threadname);
  }

  public ClockController(String name) {
    LocalDateTime now = LocalDateTime.now();
    hh = now.getHour();
    mm = now.getMinute();
    ss = now.getSecond();
    threadname = name;
    System.out.println("Creating " + threadname);
  }

  public int getJam() {
    return hh;
  }

  public int getMenit() {
    return mm;
  }

  public int getDetik() {
    return ss;
  }

  public void setJam(int jam) {
    hh = jam;
  }

  public void setMenit(int menit) {
    mm = menit;
  }

  public void setDetik(int detik) {
    ss = detik;
  }

  public void AddSecond(int second) {
    //Clock temp = new Clock();
    setDetik(getDetik() + second);

    if (getDetik() >= 60) {
      setMenit(getMenit() + getDetik() / 60);
      setDetik(getDetik() % 60);
    }

    if (getMenit() >= 60) {
      setJam(getJam() + getMenit() / 60);
      setMenit(getMenit() % 60);
    }

    if (getJam() >= 24) {
      setJam(getJam() % 24);
    }
  }

  @Override
  public void run() {
    System.out.println("Running " + threadname);
    try {
      for (int i = 4; i > 0; i--) {
        AddSecond(i);
        System.out.println("Thread: " + threadname + ", jam: " + getJam() + ", menit: " + getMenit() + ", detik: " + getDetik());
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      System.out.println("Thread " + threadname + " interrupted.");
    }
    System.out.println("Thread " + threadname + " exiting.");
  }

  public void start() {
    System.out.println("Starting " + threadname);
    if (t == null) {
      t = new Thread(this, threadname);
      t.start();
    }
  }

  @Override
  public Component init() {
    return new WebLabel("Clock!");
  }
}
