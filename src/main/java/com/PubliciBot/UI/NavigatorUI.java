package com.PubliciBot.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
@Theme("mytheme")
public class NavigatorUI extends UI {

    Navigator navigator;

    String loggedInUser;

    @Override
    public void init(VaadinRequest request) {
        // Create Navigator, make it control the ViewDisplay
        navigator = new Navigator(this, this);

        // Add some Views
        Login login= new Login();
        ABMTags aBMtags = new ABMTags();


        navigator.addView("", login);
        navigator.addView("ABMTAGS", aBMtags);
        // we'll handle permissions with a listener here, you could also do
        // that in the View itself.

        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                String user=((NavigatorUI) UI.getCurrent()).getLoggedInUser();
                if (user != null) {

                    if (event.getNewView() instanceof ABMTags) {
                        if (!user.equals("")) {
                            return true;
                        }

                    }
                }

                if(event.getNewView() instanceof Login)
                    return true;

                Notification.show("403 Acceso Denegado", Notification.Type.ERROR_MESSAGE);
                return false;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }

        });
    }

    public String getLoggedInUser(){
        return loggedInUser;
    }

    public void setLoggedInUser(String user){
        loggedInUser = user;
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {
    }
}