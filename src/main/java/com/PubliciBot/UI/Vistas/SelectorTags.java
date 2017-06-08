package com.PubliciBot.UI.Vistas;

import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.ArbolTagsService;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;

import java.util.ArrayList;

/**
 * Created by Hugo on 26/05/2017.
 */

public class SelectorTags extends Window {

    private Button seleccionar;
    private Button cerrar;
    private ArbolTagsService arbolTagService;
    private Tree arbolVaadin;
    private ArrayList<Tag> seleccionados ;

    public SelectorTags(){
        super("Seleccione los Tags para su campaña"); // Set window caption
        center();

        // Disable the close button
        setClosable(false);
        setHeight(400, Unit.PIXELS);
        setWidth(400, Unit.PIXELS);

        VerticalLayout vLayout = new VerticalLayout();
        HorizontalLayout hLayout = new HorizontalLayout();

        seleccionados = new ArrayList<Tag>();

        arbolVaadin = new Tree();
        arbolVaadin.setSelectable(true);
        arbolVaadin.setMultiSelect(true);
        arbolVaadin.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Object item = itemClickEvent.getItemId();
                Tag tag = (Tag) item;
                Tag nuevo = new Tag(tag.getNombre());
                if(!seleccionados.contains(nuevo))
                    seleccionados.add(nuevo);
                else
                    seleccionados.remove(nuevo);
            }
        });

        arbolTagService = new ArbolTagsService();
        arbolTagService.recuperarArbol();

        arbolVaadin = arbolTagService.convertirArbolaTree(arbolVaadin);

        setModal(true);

        Panel panel = new Panel("Tags disponibles");
        panel.setWidth("400px");
        panel.setHeight("300px");
        panel.setContent(arbolVaadin);

        hLayout.setSpacing(true);
        hLayout.addComponent( seleccionar = new Button("Seleccionar"));

        hLayout.addComponent( cerrar = new Button("Cerrar"));

        vLayout.addComponent(panel);
        vLayout.addComponent(hLayout);
        vLayout.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);
        setContent(vLayout);
        //setContent();
    }

    private void seleccionar()
    {

    }

    public Button getSeleccionar() {
        return seleccionar;
    }

    public Button getCerrar() {
        return cerrar;
    }

    public ArbolTagsService getArbolTagService(){
        return this.arbolTagService;
    }

    public Tree getArbolVaadin(){
        return this.arbolVaadin;
    }

    public ArrayList<Tag> getSeleccionados() {
        return seleccionados;
    }

    public void vaciarSeleccionados(){
        this.seleccionados = new ArrayList<Tag>();
    }


}