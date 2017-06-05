package com.PubliciBot.UI.Vistas;

import com.PubliciBot.UI.Vistas.Controladores.ABMCampanasController;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by Hugo on 25/05/2017.
 */
@Theme("mytheme")
public class ABMCampanasView extends HorizontalLayout implements View {
    public static final String VIEW_NAME = "Creación de Campañas";

    public ABMCampanasView()
    {
        ABMCampanasController abmCampanasController = new ABMCampanasController();
        this.addComponent(abmCampanasController);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
      //  Notification.show("Bienvenido "+((NavigatorUI) UI.getCurrent()).getLoggedInUser());

    }


}
