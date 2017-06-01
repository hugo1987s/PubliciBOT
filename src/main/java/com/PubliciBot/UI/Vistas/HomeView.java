package com.PubliciBot.UI.Vistas;

import com.PubliciBot.UI.MyUI;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    public HomeView() {
        CustomLayout aboutContent = new CustomLayout("homeview");
        aboutContent.setStyleName("about-content");

        // you can add Vaadin components in predefined slots in the custom
        // layout



        aboutContent.addComponent(
                new Label(VaadinIcons.HOME.getHtml()
                        + "  Bievenido  "
                        + MyUI.get().getAccessControl().getPrincipalName(), ContentMode.HTML), "info");

        setSizeFull();
        setMargin(false);
        setStyleName("about-view");
        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
