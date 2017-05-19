package com.PubliciBot.UI;

import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.TreeService;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Hugo on 14/05/2017.
 */
public class ABMTags extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
       com.vaadin.ui.Tree arbolDeTags = new Tree();

        Layout lo=new HorizontalLayout();
        TreeService TS=new TreeService();
     // arbolDeTags.addItem(new Tag("Raiz"));
        Tag tg=new Tag("Raiz");
        TS.agregarTag(arbolDeTags,tg);
        TS.agregarTag(arbolDeTags,tg);
    //   ArrayList<Tag> tagss=(ArrayList<Tag>)arbolDeTags.getChildren(tg);


        lo.addComponent(arbolDeTags);
      //  lo.addComponent(lb);

       setContent(lo);


    }



    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ABMTags.class, productionMode = false)
    public static class ABMTagsServlet extends VaadinServlet {

    }
}
