package com.PubliciBot.UI.Vistas;

import com.PubliciBot.UI.Vistas.Controladores.ABMAccionController;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Created by Hugo on 10/06/2017.
 */
public class ABMAccionView  extends Window {

    public static final String VIEW_NAME = "Creación de Acciones";

    public ABMAccionView()
    {
        ABMAccionController abmAccionController = new ABMAccionController();
        this.setContent(abmAccionController);
        this.setWidth("450");


    }


}
