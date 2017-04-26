package org.ensure.forgetnot.view;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;

import org.ensure.forgetnot.controller.Controller;
import org.ensure.forgetnot.core.Config;
import org.ensure.forgetnot.utility.Clock;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;



/**
 * Kelas Container.
 *
 * @author Ray
 */

public class ContainerView extends WebPanel {
  private List<Component> panelComponent = new ArrayList<>();
  private int axis;

  /**
   * Konstruktor.
   */
  ContainerView() {
    panelComponent.add(new WebLabel("Welcome, " + Config.getLoginUser() + " !"));
    panelComponent.add(Clock.clock.getClockLabel());
    Clock.clock.start();

    final ClassPathScanningCandidateComponentProvider provider =
        new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(
        new RegexPatternTypeFilter(Pattern.compile(".*"))
    );

    final Set<BeanDefinition> classes = provider.findCandidateComponents(
        "org.ensure.forgetnot.controller"
    );

    for (BeanDefinition bean : classes) {
      try {
        if (!bean
            .getBeanClassName()
            .equals("org.ensure.forgetnot.controller.MainController")
            &&
            !bean
                .getBeanClassName()
                .equals("org.ensure.forgetnot.controller.Controller")
            &&
            !bean
                .getBeanClassName()
                .equals("org.ensure.forgetnot.controller.RegisterController")
            &&
            !bean
                .getBeanClassName()
                .equals("org.ensure.forgetnot.controller.LoginController")) {
          Class<?> classController = Class.forName(bean.getBeanClassName());
          Controller c = (Controller) classController.newInstance();
          if (c.isShow()) {
            panelComponent.add(c.init());
          }
        }
      } catch (ClassNotFoundException
          | IllegalAccessException
          | InstantiationException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * setAxis untuk menetapkan axis.
   *
   * @param axis posisi
   */
  public void setAxis(int axis) {
    this.axis = axis;
  }

  /**
   * menambah komponen.
   *
   * @param elementComponent Komponen Swing yang ingin ditambah
   */
  public void addComponent(Component elementComponent) {
    panelComponent.add(elementComponent);
  }

  /**
   * Mengeset Panel Komponen.
   *
   * @param panelComponent List of komponen
   */
  public void setPanelComponent(List<Component> panelComponent) {
    this.panelComponent = panelComponent;
  }

  /**
   * Inisiasi Swing yang akan ditampilkan.
   */
  public void init() {
    setLayout(new BoxLayout(this, axis));

    for (Component component : panelComponent) {
      //System.out.println(component.getName());
      component.setMaximumSize(component.getPreferredSize());
      add(component, BorderLayout.SOUTH);
    }
  }
}