package com.PubliciBot.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Hugo on 14/05/2017.
 */
@Theme("mytheme")
public class ABMTags extends VerticalLayout implements View{

    Tree treeVaadin;

   // @Override
    //protected void init(VaadinRequest vaadinRequest)
    ABMTags()
    {
        ABMTagsController ABCTRL=new ABMTagsController(this);
        this.addComponent(ABCTRL);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
/*
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ABMTags.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {
    }
*/
}
