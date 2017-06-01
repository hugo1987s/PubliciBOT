package com.PubliciBot.UI.Vistas;

import com.PubliciBot.UI.Vistas.Controladores.ABMTagsController;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Hugo on 14/05/2017.
 */
    @Theme("mytheme")
    public class ABMTagsView extends VerticalLayout implements View{
        public static final String VIEW_NAME = "Creacion de Tags";
        Tree treeVaadin;


        // @Override
        //protected void init(VaadinRequest vaadinRequest)
        ABMTagsView()
        {
            ABMTagsController ABCTRL = new ABMTagsController(this);
            this.addComponent(ABCTRL);
        }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       // Notification.show("Bienvenido "+((NavigatorUI) UI.getCurrent()).getLoggedInUser());
    }

}
