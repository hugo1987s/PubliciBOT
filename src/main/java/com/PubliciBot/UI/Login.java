package com.PubliciBot.UI;

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
public class Login  extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layoutVertical = new VerticalLayout();
        final HorizontalLayout layoutHorizontal = new HorizontalLayout();


        TextField txtUsuario = new TextField("Usuario: ");
        txtUsuario.setMaxLength(30);


        PasswordField txtContrasena = new PasswordField("Contraseña: ");
        txtContrasena.setMaxLength(30);

        Button btnIngresar = new Button("Ingresar");

        layoutVertical.setWidthUndefined();

        layoutVertical.addComponent(txtUsuario);
        layoutVertical.addComponent(txtContrasena);
        layoutVertical.addComponent(btnIngresar);
        layoutVertical.setWidth(100, Unit.PERCENTAGE);



       // layoutHorizontal.addComponent(layoutVertical);
       // layoutHorizontal.setWidth(100, Unit.PERCENTAGE);
       // layoutHorizontal.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        //layoutHorizontal.setComponentAlignment(layoutVertical, Alignment.MIDDLE_CENTER);


        setContent(layoutVertical);


    }
/*
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Login.class, productionMode = false)
    public static class LoginServlet extends VaadinServlet {

    }
*/
}
