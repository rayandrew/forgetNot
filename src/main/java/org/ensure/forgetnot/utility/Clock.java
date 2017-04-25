package org.ensure.forgetnot.utility;

import java.time.LocalDateTime;

/**
 * Created by agath on 24/04/2017.
 */
public class Clock implements Runnable {
  private int hh, mm, ss;
  private Thread t;
  private String threadname;

  public Clock() {
    LocalDateTime now = LocalDateTime.now();
    hh = now.getHour();
    mm = now.getMinute();
    ss = now.getSecond();
    threadname = "a";
    System.out.println("Creating " + threadname);
  }

  public Clock(String name) {
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

    //return temp;
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

  /*public static void main(String args[]){
    Clock C1 = new Clock("Clock1");
    C1.start();

    Clock C2 = new Clock("Clock2");
    C2.start();
  }*/
}
