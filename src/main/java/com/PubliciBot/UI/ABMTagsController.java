package com.PubliciBot.UI;

import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.ArbolTagsService;
import com.vaadin.ui.*;

public class ABMTagsController extends VerticalLayout {
    Tree treeVaadin;

    public ABMTagsController(ABMTags abmtag){
        super();
        treeVaadin=new Tree();



        ArbolTagsService TS = new ArbolTagsService();

        TextField txtNuevoTag = new TextField("");
        txtNuevoTag.setMaxLength(30);
        Label Title=new Label("Administracion de Tags");
        Button btnAgregarTag = new Button("Agregar");
        Button btneliminarTag = new Button("Eliminar");

        TS.recuperarArbol();
        treeVaadin = TS.convertirArbolaTree(treeVaadin);


        btnAgregarTag.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Tag nuevo=null ;
                Tag temp=null;

                if (txtNuevoTag.getValue().trim() != "") {
                    nuevo=new Tag(txtNuevoTag.getValue());

                    txtNuevoTag.setValue("");
                }

                temp = (Tag) treeVaadin.getValue();

                if (temp != null&&nuevo!=null) {
                    TS.agregarTag(treeVaadin,nuevo);
                    TS.setearPadre(treeVaadin, nuevo, temp);
                }
                else if(nuevo!=null) {
                    TS.agregarTag(treeVaadin, nuevo);
                }
                if(nuevo==null){
                    abmtag.showNotification("No es posible agregar un tag Vacio");
                }
                abmtag.showNotification(TS.getArbolTags().getTags().toString());
                TS.guardarArbol();

            }
        });

        btneliminarTag.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Tag temp = (Tag) treeVaadin.getValue();

                if (temp != null)
                    TS.quitarTag(treeVaadin, temp);
                if(temp==null)
                    abmtag.showNotification("No se ha seleccionado ningun tag");
                abmtag.showNotification(TS.getArbolTags().getTags().toString());
                TS.guardarArbol();

            }
        });
        HorizontalLayout HL=new HorizontalLayout();
        HL.addComponent(txtNuevoTag);
        HL.addComponent(btnAgregarTag);
        HL.setComponentAlignment(txtNuevoTag,Alignment.MIDDLE_CENTER);
        HL.setComponentAlignment(btnAgregarTag,Alignment.BOTTOM_CENTER);
        this.addComponent(Title);
        this.setComponentAlignment(Title,Alignment.BOTTOM_CENTER);
        this.addComponent(HL);
        this.addComponent(treeVaadin);
        HorizontalLayout HL2=new HorizontalLayout();


        HL2.addComponent(btneliminarTag);
        HL2.setComponentAlignment(btneliminarTag,Alignment.BOTTOM_RIGHT);
        HL2.setSpacing(true);
        this.addComponent(HL2);


    }


}
