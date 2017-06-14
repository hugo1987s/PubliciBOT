package com.PubliciBot.UI.Vistas;

import com.PubliciBot.UI.Vistas.Controladores.ABMAccionController;
import com.PubliciBot.UI.Vistas.Controladores.ABMCampanasController;
import com.vaadin.ui.Window;

/**
 * Created by Hugo on 10/06/2017.
 */
public class ABMAccionView  extends Window {

    public static final String VIEW_NAME = "Creación de Acciones";

    public ABMAccionView(ABMCampanasController controller)
    {
        ABMAccionController abmAccionController = new ABMAccionController(controller);
        this.setContent(abmAccionController);
        this.setWidth("450");


    }


}
