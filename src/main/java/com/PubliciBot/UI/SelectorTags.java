package com.PubliciBot.UI;

import com.PubliciBot.Services.ArbolTagsService;
import com.PubliciBot.Services.TreeService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Created by Hugo on 26/05/2017.
 */

public class SelectorTags extends Window {

    public SelectorTags(){
        super("Seleccione los Tags para su campaña"); // Set window caption
        center();

        // Disable the close button
        setClosable(false);
        setHeight(400, Unit.PIXELS);
        setWidth(400, Unit.PIXELS);

        VerticalLayout vLayout = new VerticalLayout();
        HorizontalLayout hLayout = new HorizontalLayout();


        Tree arbolVaadin = new Tree();

        ArbolTagsService arbolTagService = new ArbolTagsService();
        arbolTagService.recuperarArbol();

        arbolVaadin = arbolTagService.convertirArbolaTree(arbolVaadin);


        setModal(true);


        Panel panel = new Panel("Tags disponibles");
        panel.setWidth("400px");
        panel.setHeight("300px");
        panel.setContent(arbolVaadin);

        hLayout.setSpacing(true);
        hLayout.addComponent(new Button("Seleccionar", event -> seleccionar()));

        hLayout.addComponent(new Button("Cerrar", event -> close()));


        vLayout.addComponent(panel);
        vLayout.addComponent(hLayout);
        vLayout.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);
        setContent(vLayout);
        //setContent();


    }

    private void seleccionar()
    {

    }
}