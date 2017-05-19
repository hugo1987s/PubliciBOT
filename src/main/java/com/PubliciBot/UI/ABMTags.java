package com.PubliciBot.UI;

import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.TreeService;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

/**
 * Created by Hugo on 14/05/2017.
 */
public class ABMTags extends UI {
    public Label createLabel(String msg){
       return new Label(msg);
    }



    @Override
    protected void init(VaadinRequest vaadinRequest) {
       com.vaadin.ui.Tree arbolDeTags = new Tree();

        Layout lo=new HorizontalLayout();
        TreeService TS=new TreeService();
        Tag tg=new Tag("Raiz");
        TS.agregarTag(arbolDeTags,tg);
        TS.agregarTag(arbolDeTags,tg);

       ArrayList<Object> rootIds =
                new ArrayList<Object>(arbolDeTags.rootItemIds());


        for (Object item :rootIds
             ) {
            Tag tag=(Tag) item;
            lo.addComponent(createLabel(((Tag) item).getNombre()));

        }

        //   ArrayList<Tag> tagss=(ArrayList<Tag>)arbolDeTags.getChildren(tg);

        lo.addComponent(arbolDeTags);
        setContent(lo);


    }



    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ABMTags.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {

    }
}
