package org.ensure.forgetnot.model;

import org.javalite.activejdbc.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rufus on 4/12/2017.
 */
public class Employee extends Model {
  static final Logger logger = LoggerFactory.getLogger(Employee.class);

  static {
    validatePresenceOf("first_name", "last_name");
  }

  public static void createEmployee() {
    Employee e = new Employee();
    e.set("first_name", "John");
    e.set("last_name", "Doe");
    e.saveIt();
  }

  public static void selectEmployee() {
    Employee e = Employee.findFirst("first_name = ?", "John");
    logger.info(e.getString("first_name"));
  }

  public static void updateEmployee() {
    Employee e = Employee.findFirst("first_name = ?", "John");
    e.set("last_name", "Steinbeck").saveIt();
  }

  public static void deleteEmployee() {
    Employee e = Employee.findFirst("first_name = ?", "John");
    e.delete();
  }

  public static void deleteAllEmployees() {
    Employee.deleteAll();
  }

  public static void selectAllEmployees() {
    logger.info("Employees list: " + Employee.findAll());
  }
}
