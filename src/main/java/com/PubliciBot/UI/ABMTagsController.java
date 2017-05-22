package com.PubliciBot.UI;

import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.ArbolTagsService;
import com.vaadin.ui.*;

public class ABMTagsController extends VerticalLayout {
    Tree treeVaadin;

    public ABMTagsController(){
        super();
        treeVaadin=new Tree();
        //arbol3Tags = new ArbolTags();


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


        this.addComponent(txtNuevoTag);
       this.addComponent(btnAgregarTag);
        this.addComponent(btneliminarTag);
        this.addComponent(treeVaadin);
        this.addComponent(btnGuardarArbol);
        this.addComponent(btnRecuperarArbol);





    }


}
