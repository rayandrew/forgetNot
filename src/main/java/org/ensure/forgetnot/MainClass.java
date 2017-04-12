package org.ensure.forgetnot;

import org.ensure.forgetnot.model.Employee;
import org.ensure.forgetnot.model.ModelCheck;
import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by rufus on 4/12/2017.
 */
public class MainClass {
  static final Logger logger = LoggerFactory.getLogger(MainClass.class);

  public static void main(String[] args) {
    ModelCheck.createNewDatabase("test.db");
    ModelCheck.createNewTable("test.db");
    Base.open("org.sqlite.JDBC", "jdbc:sqlite:./data/test.db", "", "");
    createEmployee();
    logger.info("=========> Created employee:");
    selectEmployee();
    updateEmployee();
    logger.info("=========> Updated employee:");
    selectAllEmployees();
    deleteEmployee();
    logger.info("=========> Deleted employee:");
    selectAllEmployees();
    createEmployee();
    logger.info("=========> Created employee:");
    selectEmployee();
    deleteAllEmployees();
    logger.info("=========> Deleted all employees:");
    selectAllEmployees();

    Base.close();
  }

  private static void createEmployee() {
    Employee e = new Employee();
    e.set("first_name", "John");
    e.set("last_name", "Doe");
    e.saveIt();
  }

  private static void selectEmployee() {
    Employee e = Employee.findFirst("first_name = ?", "John");
    logger.info(e.toString());
  }

  private static void updateEmployee() {
    Employee e = Employee.findFirst("first_name = ?", "John");
    e.set("last_name", "Steinbeck").saveIt();
  }

  private static void deleteEmployee() {
    Employee e = Employee.findFirst("first_name = ?", "John");
    e.delete();
  }

  private static void deleteAllEmployees() {
    Employee.deleteAll();
  }

  private static void selectAllEmployees() {
    logger.info("Employees list: " + Employee.findAll());
  }
}
