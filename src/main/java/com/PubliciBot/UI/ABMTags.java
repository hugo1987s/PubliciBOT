package com.PubliciBot.UI;

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

    Tree treeVaadin;
    public Label createLabel(String msg) {
        return new Label(msg);
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        treeVaadin=new Tree();
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
                    Tag nuevo=null ;
                    Tag temp=null;

                    if (txtNuevoTag.getValue().trim() != "") {
                        nuevo=new Tag(txtNuevoTag.getValue());

                    txtNuevoTag.setValue("");
                }

                temp = (Tag) treeVaadin.getValue();

                if (temp != null) {
                     TS.agregarTag(treeVaadin,nuevo);
                     TS.setearPadre(treeVaadin, nuevo, temp);
                }
                else if(nuevo!=null) {
                    TS.agregarTag(treeVaadin, nuevo);
                }

               // TS.cargarTreeVaadin(arbol2Tags, treeVaadin);
/*
                if (txtNuevoTag.getValue().trim() != "") {
                    nuevo.setNombre(txtNuevoTag.getValue());

                    txtNuevoTag.setValue("");
                }

                temp = (Tag) treeVaadin.getValue();
                if (temp != null) {
                    TS.setearPadre(treeVaadin, nuevo, temp);

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
                Tag temp = (Tag) treeVaadin.getValue();

                if (temp != null)
                    TS.quitarTag(treeVaadin, temp);

            }
        });


        btnGuardarArbol.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //TS.guardarArbol(arbolDeTags);
                //arbol2Tags.PersistirArbol();
                TS.guardarArbol();
            }
        });


        btnRecuperarArbol.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                TS.recuperarArbol();

                treeVaadin = TS.convertirArbolaTree(treeVaadin);
            }
        });


        lo.addComponent(txtNuevoTag);
        lo.addComponent(btnAgregarTag);
        lo.addComponent(btneliminarTag);
        lo.addComponent(treeVaadin);
        lo.addComponent(btnGuardarArbol);
        lo.addComponent(btnRecuperarArbol);


        setContent(lo);


    }





    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ABMTags.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {

    }
}
