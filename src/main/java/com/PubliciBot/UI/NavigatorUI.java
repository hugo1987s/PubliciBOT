package com.PubliciBot.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

/**
 * Created by monzon on 24/05/17.
 */

@Theme("mytheme")
public class NavigatorUI extends UI {



    @Override
    protected void init(VaadinRequest request) {

        final Navigator navigator = new Navigator(this, this);
        Login login= new Login(navigator);
        ABMTags aBMtags = new ABMTags();
        final VerticalLayout layout = new VerticalLayout();

        navigator.addView("", login);
        navigator.addView("ABMTAGS", aBMtags);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {
    }
}
