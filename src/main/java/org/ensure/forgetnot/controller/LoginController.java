package org.ensure.forgetnot.controller;

import java.awt.Button;
import java.awt.Component;

import org.ensure.forgetnot.core.Controller;

/**
 * Created by rufus on 4/22/2017.
 */
public class LoginController extends Controller {
  public Component init() {
    return new Button("Login");
  }
}