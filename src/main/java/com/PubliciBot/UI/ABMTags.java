package com.PubliciBot.UI;

import com.PubliciBot.DM.ArbolTags;
import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.ArbolTagsService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Hugo on 14/05/2017.
 */
@Theme("mytheme")
public class ABMTags extends UI {

    ArbolTags arbol2Tags;
    public Label createLabel(String msg) {
        return new Label(msg);
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        com.vaadin.ui.Tree arbolContenedorDeTags = new Tree();

        arbol2Tags = new ArbolTags();
        //arbol3Tags = new ArbolTags();

        Layout lo = new VerticalLayout();
        ArbolTagsService TS = new ArbolTagsService();

        TextField txtNuevoTag = new TextField("");
        txtNuevoTag.setMaxLength(30);

        Button btnAgregarTag = new Button("Agregar");
        Button btneliminarTag = new Button("Eliminar");

        Button btnGuardarArbol = new Button("Guardar Tree");
        Button btnRecuperarArbol = new Button("Recuperar Tree");

        //TODO mover esto a un controller?
        //TODO MODIFICAR SOLO EL TREE Y GENERAR DE NUEVO EL ARBOLTAG
        //TODO CADA VEZ QUE HAYAN CAMBIOS PERSISTIR EL ARBOL
        btnAgregarTag.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                    Tag nuevo = new Tag("");
                    Tag temp = new Tag("");

                    if (txtNuevoTag.getValue().trim() != "") {
                    nuevo.setNombre(txtNuevoTag.getValue());

                    txtNuevoTag.setValue("");
                }

                temp = (Tag) arbolContenedorDeTags.getValue();

                if (temp != null) {
                     arbol2Tags.AgregarTag(nuevo.getNombre(), temp.getNombre());
                }
                else
                    arbol2Tags.AgregarTag(nuevo);


                cargarTreeVaadin(arbol2Tags, arbolContenedorDeTags);
/*
                if (txtNuevoTag.getValue().trim() != "") {
                    nuevo.setNombre(txtNuevoTag.getValue());

                    txtNuevoTag.setValue("");
                }

                temp = (Tag) arbolContenedorDeTags.getValue();
                if (temp != null) {
                    TS.setearPadre(arbolContenedorDeTags, nuevo, temp);

                }
*/
                /*
                if(txtNuevoTag.getValue().trim() != "") {
                    nuevo.setNombre(txtNuevoTag.getValue());
                    TS.agregarTag(arbolDeTags, nuevo);
                    txtNuevoTag.setValue("");
                }

                temp = (Tag) arbolDeTags.getValue();
                if(temp != null)
                    TS.setearPadre(arbolDeTags, nuevo, temp);
                */

            }
        });

        btneliminarTag.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Tag temp = (Tag) arbolContenedorDeTags.getValue();

                if (temp != null)
                    TS.quitarTag(arbolContenedorDeTags, temp);

            }
        });


        btnGuardarArbol.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //TS.guardarArbol(arbolDeTags);
                //arbol2Tags.PersistirArbol();
                TS.guardarArbol(arbol2Tags);
            }
        });


        btnRecuperarArbol.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                arbol2Tags = TS.recuperarArbol();
                //arbol3Tags = arbol2Tags.RecuperarArbol();

                cargarTreeVaadin(arbol2Tags, arbolContenedorDeTags);
            }
        });


        lo.addComponent(txtNuevoTag);
        lo.addComponent(btnAgregarTag);
        lo.addComponent(btneliminarTag);
        lo.addComponent(arbolContenedorDeTags);
        lo.addComponent(btnGuardarArbol);
        lo.addComponent(btnRecuperarArbol);


        setContent(lo);


    }
    //TODO mover esto a Tree service
    private void cargarTreeVaadin(ArbolTags arbolTags, Tree treeVaadin) {
        //Limpio el arbol para no repetir los items
        ArbolTagsService TS = new ArbolTagsService();

        treeVaadin.removeAllItems();

        for (Tag tag : arbolTags.getTags()) {
            treeVaadin.addItem(tag);
        }

        for (Tag tag : arbolTags.getTags()) {
            if (tag.getNombreTagPadre() != null)
                treeVaadin.setParent(tag, TS.buscarTagPorPadre(arbolTags, tag.getNombreTagPadre()));
        }

    }




    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ABMTags.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {

    }
}
