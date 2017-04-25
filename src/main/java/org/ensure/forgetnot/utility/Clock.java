package org.ensure.forgetnot.utility;

import com.alee.laf.label.WebLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.SwingUtilities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agath on 24/04/2017.
 */
public class Clock implements Runnable {
  static final Logger logger = LoggerFactory.getLogger(Clock.class);
  private int hh, mm, ss;
  private Thread t;
  private String threadname;
  public static Clock clock = new Clock("ClockController");
  public boolean stopStatus = false;
  private WebLabel clockLabel = new WebLabel();


  public Clock() {
    LocalDateTime now = LocalDateTime.now();
    hh = now.getHour();
    mm = now.getMinute();
    ss = now.getSecond();
    threadname = "ClockControler";
    clockLabel.setText(this.toString());
    logger.info("Creating " + threadname);
  }

  public Clock(String name) {
    LocalDateTime now = LocalDateTime.now();
    hh = now.getHour();
    mm = now.getMinute();
    ss = now.getSecond();
    threadname = name;
    clockLabel.setText(this.toString());
    logger.info("Creating " + threadname);
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
    logger.info("Running " + threadname);
    try {
      while (!stopStatus) {
        AddSecond(1);
        logger.info("Thread: "
            + threadname
            + ", jam: "
            + getJam()
            + ", menit: "
            + getMenit()
            + ", detik: "
            + getDetik()
        );
        SwingUtilities.invokeLater(() -> clockLabel.setText(this.toString()));
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      logger.info("Thread " + threadname + " interrupted.");
    }
    logger.info("Thread " + threadname + " exiting.");
  }

  public void start() {
    logger.info("Starting " + threadname);
    if (t == null) {
      t = new Thread(this, threadname);
      t.start();
    }
  }

  public void stop() {
    stopStatus = true;
  }

  public WebLabel getClockLabel() {
    return clockLabel;
  }

  @Override
  public String toString() {
    if (getJam() < 10) {
      return "0" + getJam() + ":" + getMenit() + ":" + getDetik();
    } else {
      return getJam() + ":" + getMenit() + ":" + getDetik();
    }
  }
}
