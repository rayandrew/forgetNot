package org.ensure.forgetnot.core;

import org.ensure.forgetnot.utility.Pair;
import org.javalite.activejdbc.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rufus on 4/24/2017.
 */
public class Database {
  private static List<Pair<String, String>> dbSetting = new ArrayList<>();

  static {
    dbSetting.add(new Pair<>("dbName", "MariaDB"));
    dbSetting.add(new Pair<>("dbDriver", "com.mysql.cj.jdbc.Driver"));
    dbSetting.add(new Pair<>("dbUser", "root"));
    dbSetting.add(new Pair<>("dbPass", ""));
    dbSetting.add(new Pair<>(
        "dbUrl",
        "jdbc:mysql://localhost/forgetNot?nullNamePatternMatchesAll=true")
    );
  }

  public static String getDbSetting(String key) {
    for (Pair<String, String> p : dbSetting) {
      if (p.getLeft().equals(key)) {
        return p.getRight();
      }
    }
    return null;
  }

  public static void connect() {
    Base.open(getDbSetting("dbDriver"),
        getDbSetting("dbUrl"),
        getDbSetting("dbUser"),
        getDbSetting("dbPass")
    );
  }

  public static void close() {
    Base.close();
  }
}
