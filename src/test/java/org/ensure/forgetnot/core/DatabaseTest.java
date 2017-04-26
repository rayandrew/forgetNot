package org.ensure.forgetnot.core;

import org.junit.Test;

/**
 * Created by Aldrich on 4/26/2017.
 */
public class DatabaseTest {
  public DatabaseTest() {

  }

  @Test
  public void connect() throws Exception {
    Database.connect();
    Database.close();
    //Jika koneksinya benar maka tidak akan ada Exception
  }

  @Test
  public void close() throws Exception {
    Database.connect();
    Database.close();
  }

}