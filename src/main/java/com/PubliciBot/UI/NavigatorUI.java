package com.PubliciBot.UI;

import com.PubliciBot.DM.Usuario;
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


    Usuario loggedInUser;

    //TODO ELABORAR TESTS DE TODO LO QUE SE NOS ROMPIO ALGUNA VEZ Y MAS
    //TODO RESOLVER TEMA LOGINS PRIVILEGIOS ---> MAXI LO QUIERE HACER
    //TODO CRAR PANTALLA ABM USUARIOS
    //TODO CREAR PANTALLA CAMPAÑAS--->Hugo?
    @Override
    public void init(VaadinRequest request) {
        // Create Navigator, make it control the ViewDisplay
        navigator = new Navigator(this, this);

        // Add some Views
        Login login = new Login();
        ABMTags aBMtags = new ABMTags();
        ABMCampanas abmCampanas = new ABMCampanas();


        navigator.addView("", login);
        navigator.addView("ABMTAGS", aBMtags);
        navigator.addView("ABMCAMPANAS", abmCampanas);

        // we'll handle permissions with a listener here, you could also do
        // that in the View itself.

        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                Usuario user = ((NavigatorUI) UI.getCurrent()).getLoggedInUser();

                if (event.getNewView() instanceof ABMTags) {
                    return true;
                }

                if(event.getNewView() instanceof ABMCampanas)
                    return true;

                if(event.getNewView() instanceof Login)
                    return true;

                if(event.getOldView() instanceof Login)
                    Notification.show("Credenciales incorrectas", Notification.Type.HUMANIZED_MESSAGE);
                else
                    Notification.show("403 Acceso Denegado", Notification.Type.ERROR_MESSAGE);
                return false;

            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }

        });
    }

    public Usuario getLoggedInUser(){
        return loggedInUser;
    }

    public void setLoggedInUser(Usuario user){
        loggedInUser = user;
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {
    }
}