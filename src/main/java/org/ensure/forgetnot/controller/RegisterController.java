package org.ensure.forgetnot.controller;

import java.awt.Button;
import java.awt.Component;

import org.ensure.forgetnot.core.Controller;

/**
 * Created by rufus on 4/23/2017.
 */
public class RegisterController extends Controller {
  @Override
  public Component init() {
    return new Button("Register");
  }
}
