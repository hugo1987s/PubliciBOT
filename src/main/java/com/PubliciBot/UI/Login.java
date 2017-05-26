package com.PubliciBot.UI;

import com.PubliciBot.DM.Usuario;
import com.PubliciBot.Services.UsuarioService;
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
    private UsuarioService userService ;

    public Login ()
    {

        final VerticalLayout layoutVertical = new VerticalLayout();
        final HorizontalLayout layoutHorizontal = new HorizontalLayout();

        this.userService = new UsuarioService();

        TextField txtUsuario = new TextField();
        txtUsuario.setMaxLength(30);
        txtUsuario.setInputPrompt("Ingrese su Email");


        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setMaxLength(30);
        txtContrasena.setInputPrompt("Ingrese su contraseña");

        Button btnIngresar = new Button("Ingresar");
        Button btnTecnico = new Button("Tecnico");

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
        GridLayout grid = new GridLayout(1, 5);

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

        grid.addComponent(btnTecnico,0, 4);
        grid.setComponentAlignment(btnTecnico, Alignment.TOP_CENTER);

        txtUsuario.setValue("ivocliente@megafono.com");
        txtContrasena.setValue("soycliente");

        btnTecnico.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                txtUsuario.setValue("ivotecnico@megafono.com");
                txtContrasena.setValue("soytecnico");
            }
        });


        //TODO se va a crear un usuario nuevo y este se va a comparar con el que exista en la base de datos
        btnIngresar.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                String userMail = txtUsuario.getValue();
                String userPassword = txtContrasena.getValue();
                boolean invalidUser = userMail.equals("");
                boolean invalidPassword = userPassword.equals("");
                NavigatorUI currentNavigator = ((NavigatorUI)UI.getCurrent());

                if(!invalidUser && !invalidPassword ){
                    Usuario user = null;
                    String savedUserMail;
                    String savedUserPassword;

                    for(Usuario savedUser : userService.getSystemUsers()){
                        savedUserMail = savedUser.getMail();
                        savedUserPassword = savedUser.getContrasena();
                        if(savedUserMail.equals(userMail) && savedUserPassword.equals(userPassword)) {
                            user = savedUser;
                            System.out.println(user.getRol().getListaPrivilegios());
                        }
                    }

                    if(user != null){
                        currentNavigator.setLoggedInUser(user);
                        boolean esTecnico = user.getRol().tienePrivilegio("class com.PubliciBot.UI.ABMTags");
                        boolean esCliente = user.getRol().tienePrivilegio("class com.PubliciBot.UI.ABMCampanas");
                        System.out.println(esCliente);
                        if(esTecnico)
                            getUI().getNavigator().navigateTo("ABMTAGS");
                        else if(esCliente)
                            getUI().getNavigator().navigateTo("ABMCAMPANAS");
                    }
                    else{
                        Notification.show("Usuario y/o contraseña incorrectos", Notification.Type.HUMANIZED_MESSAGE);
                    }
                }
                else{
                    Notification.show("Usuario y/o contraseña incorrectos", Notification.Type.HUMANIZED_MESSAGE);
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
