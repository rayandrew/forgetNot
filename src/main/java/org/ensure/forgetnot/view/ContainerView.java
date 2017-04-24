package org.ensure.forgetnot.view;

import com.alee.laf.panel.WebPanel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;

import org.ensure.forgetnot.controller.Controller;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

/**
 * Created by rufus on 4/15/2017.
 */

public class ContainerView extends WebPanel {
  private List<Component> panelComponent = new ArrayList<Component>();
  private int axis;

  public ContainerView() {
    final ClassPathScanningCandidateComponentProvider provider =
        new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(
        new RegexPatternTypeFilter(Pattern.compile(".*"))
    );

    final Set<BeanDefinition> classes = provider.findCandidateComponents(
        "org.ensure.forgetnot.controller"
    );

    //System.out.println("List of controller :");
    for (BeanDefinition bean : classes) {
      //System.out.println(bean.getBeanClassName());
      try {
        if (!bean
            .getBeanClassName()
            .equals("org.ensure.forgetnot.controller.MainController")
            &&
            !bean
            .getBeanClassName()
            .equals("org.ensure.forgetnot.controller.Controller")) {
          Class<?> classController = Class.forName(bean.getBeanClassName());
          Controller c = (Controller) classController.newInstance();
          panelComponent.add(c.init());
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    for (Component c : panelComponent) {
      System.out.println(c.getName());
    }
  }

  public void setAxis(int axis) {
    this.axis = axis;
  }


  public void addComponent(Component elementComponent) {
    panelComponent.add(elementComponent);
  }

  public void setPanelComponent(List<Component> panelComponent) {
    this.panelComponent = panelComponent;
  }

  public void init() {
    setLayout(new BoxLayout(this, axis));

    for (Component component : panelComponent) {
      System.out.println(component.getName());
      add(component);
    }
  }
}