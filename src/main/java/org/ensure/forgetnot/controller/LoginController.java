package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Controller;

import java.awt.Button;
import java.awt.Component;

/**
 * Created by rufus on 4/22/2017.
 */
public class LoginController extends Controller {
  public Component init() {
    return new Button("Login");
  }
}
