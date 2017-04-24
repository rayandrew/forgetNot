package org.ensure.forgetnot.controller;

import org.ensure.forgetnot.core.Controller;

import java.awt.Button;
import java.awt.Component;

/**
 * Created by rufus on 4/23/2017.
 */
public class RegisterController extends Controller {
  @Override
  public Component init() {
    return new Button("Register");
  }
}
