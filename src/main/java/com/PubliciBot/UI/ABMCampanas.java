package com.PubliciBot.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Hugo on 25/05/2017.
 */
@Theme("mytheme")
public class ABMCampanas  extends VerticalLayout implements View {


    public ABMCampanas()
    {
        ABMCampanasController abmCampanasController = new ABMCampanasController();
        this.addComponent(abmCampanasController);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("Bienvenido "+((NavigatorUI) UI.getCurrent()).getLoggedInUser());
    }


}
