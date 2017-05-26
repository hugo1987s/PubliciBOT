package com.PubliciBot.UI;

import com.PubliciBot.DM.Privilegio;
import com.PubliciBot.DM.Rol;
import com.PubliciBot.DM.Usuario;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

/**
 * Created by Hugo on 14/05/2017.
 */

@Theme("mytheme")
public class Login extends VerticalLayout implements View {


    //protected void init(VaadinRequest vaadinRequest)

    public Login ()
    {

        final VerticalLayout layoutVertical = new VerticalLayout();
        final HorizontalLayout layoutHorizontal = new HorizontalLayout();

        TextField txtUsuario = new TextField();
        txtUsuario.setMaxLength(30);
        txtUsuario.setInputPrompt("Ingrese su Email");


        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setMaxLength(30);
        txtContrasena.setInputPrompt("Ingrese su contraseña");

        Button btnIngresar = new Button("Ingresar");


        ////////////

        // Find the application directory
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

// Image as a file resource
        FileResource resource = new FileResource(new File(basepath +
                "/WEB-INF/images/Megafono4.PNG"));

// Show the image in the application
        Image image = new Image("", resource);


        ///////////

        // Create a grid layout
        GridLayout grid = new GridLayout(1, 4);

        grid.setWidth(600, Sizeable.UNITS_PIXELS);
        grid.setHeight(400, Sizeable.UNITS_PIXELS);

        grid.addComponent(image, 0, 0);
        grid.setComponentAlignment(image, Alignment.TOP_CENTER);

        grid.addComponent(txtUsuario, 0, 1);
        grid.setComponentAlignment(txtUsuario, Alignment.TOP_CENTER);

        grid.addComponent(txtContrasena, 0, 2);
        grid.setComponentAlignment(txtContrasena, Alignment.TOP_CENTER);

        grid.addComponent(btnIngresar, 0, 3);
        grid.setComponentAlignment(btnIngresar, Alignment.TOP_CENTER);


        //TODO se va a crear un usuario nuevo y este se va a comparar con el que exista en la base de datos
        btnIngresar.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                String userName = txtUsuario.getValue();
                String password = txtContrasena.getValue();
                boolean invalidUser = userName.equals("");
                boolean invalidPassword = password.equals("");
                if(!invalidUser && !invalidPassword){
                    Privilegio<ABMTags> privilegioTecnico = new Privilegio<>(ABMTags.class);
                    Rol role = new Rol("Tecnico");
                    role.add(privilegioTecnico);
                    Usuario user = new Usuario(userName,password,role);
                    NavigatorUI currentNavigator = ((NavigatorUI)UI.getCurrent());
                    currentNavigator.setLoggedInUser(user);
                    getUI().getNavigator().navigateTo("ABMTAGS");
                }

            }});





        this.setSizeFull();
        this.addComponent(grid);
        this.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);


/*
        layoutVertical.setHeight(600, Unit.PIXELS);

        layoutVertical.addComponent(image);
        layoutVertical.addComponent(txtUsuario);
        layoutVertical.addComponent(txtContrasena);
        layoutVertical.addComponent(btnIngresar);

        layoutVertical.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
        layoutVertical.setComponentAlignment(txtUsuario, Alignment.MIDDLE_CENTER);
        layoutVertical.setComponentAlignment(txtContrasena, Alignment.MIDDLE_CENTER);
        layoutVertical.setComponentAlignment(btnIngresar, Alignment.MIDDLE_CENTER);

*/

        // layoutHorizontal.addComponent(layoutVertical);
        // layoutHorizontal.setWidth(100, Unit.PERCENTAGE);
        // layoutHorizontal.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        //layoutHorizontal.setComponentAlignment(layoutVertical, Alignment.MIDDLE_CENTER);


        //setContent(this);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
/*
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Login.class, productionMode = false)
    public static class LoginServlet extends VaadinServlet {

    }*/
}
