package com.PubliciBot.UI;

import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.TreeService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**
 * Created by Hugo on 14/05/2017.
 */
@Theme("mytheme")
public class ABMTags extends UI {
    public Label createLabel(String msg){
       return new Label(msg);
    }



    @Override
    protected void init(VaadinRequest vaadinRequest) {
        com.vaadin.ui.Tree arbolDeTags = new Tree();

        Layout lo = new VerticalLayout();
        TreeService TS = new TreeService();

        TextField txtNuevoTag = new TextField("");
        txtNuevoTag.setMaxLength(30);

        Button btnAgregarTag = new Button("Agregar");
        Button btneliminarTag = new Button ("Eliminar");

        btnAgregarTag.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Tag nuevo = new Tag("");
                Tag temp = new Tag("");

                if(txtNuevoTag.getValue().trim() != "") {
                    nuevo.setNombre(txtNuevoTag.getValue());
                    TS.agregarTag(arbolDeTags, nuevo);
                    txtNuevoTag.setValue("");
                }

                temp = (Tag) arbolDeTags.getValue();
                if(temp != null)
                    TS.setearPadre(arbolDeTags, nuevo, temp);

            }
        });
        btneliminarTag.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Tag temp = (Tag)arbolDeTags.getValue() ;

                if(temp != null)
                    TS.quitarTag(arbolDeTags, temp);

            }
        });


        Tag tg = new Tag("Raiz");
        Tag tg1 = new Tag("Test2");
        Tag tg2 = new Tag("Test3");
        Tag tg3 = new Tag("Test2.1");
        Tag tg4 = new Tag("Test4");
        Tag tg5 = new Tag("Test2");
        Tag tg6 = new Tag("Test2.1.1");
        Tag tg7 = new Tag("Test4.1");
        Tag tg8 = new Tag("Test4.1.1");

        TS.agregarTag(arbolDeTags,tg);
        TS.agregarTag(arbolDeTags,tg);
        TS.agregarTag(arbolDeTags,tg1);
        TS.agregarTag(arbolDeTags,tg2);
        TS.agregarTag(arbolDeTags,tg3);
        TS.agregarTag(arbolDeTags,tg4);
        TS.agregarTag(arbolDeTags,tg5);
        TS.agregarTag(arbolDeTags,tg6);
        TS.agregarTag(arbolDeTags,tg7);
        TS.agregarTag(arbolDeTags,tg8);


        System.out.println(TS.setearPadre(arbolDeTags, tg7,  tg4));
        System.out.println(TS.setearPadre(arbolDeTags, tg3,  tg1));
        System.out.println(TS.setearPadre(arbolDeTags, tg6,  tg1));
        System.out.println(TS.setearPadre(arbolDeTags, tg8,  tg4));




        //TS.setearPadre(arbolDeTags, )

       ArrayList<Object> rootIds =
                new ArrayList<Object>(arbolDeTags.rootItemIds());

/*
        for (Object item :rootIds
             ) {
            Tag tag=(Tag) item;
            lo.addComponent(createLabel(((Tag) item).getNombre()));

        }
*/
        //   ArrayList<Tag> tagss=(ArrayList<Tag>)arbolDeTags.getChildren(tg);

        lo.addComponent(txtNuevoTag);
        lo.addComponent(btnAgregarTag);
        lo.addComponent(btneliminarTag);
        lo.addComponent(arbolDeTags);
        setContent(lo);


    }



    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ABMTags.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {

    }
}
