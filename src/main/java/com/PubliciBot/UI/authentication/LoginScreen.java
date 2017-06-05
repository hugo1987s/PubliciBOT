package com.PubliciBot.UI.authentication;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.Serializable;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginScreen extends CssLayout {

    private TextField username;
    private PasswordField password;
    private Button login;
    private Button forgotPassword;
    private Button changeControl;   //boton para cambiar entre control basico a estricto
    private LoginListener loginListener;
    private AccessControl accessControl;
    private MenuControl control;


    public LoginScreen(AccessControl accessControl, LoginListener loginListener) {
        this.loginListener = loginListener;
        this.accessControl = accessControl;
        this.control = MenuControl.basic;
        buildUI();
        username.focus();
    }

    private void buildUI() {
        addStyleName("login-screen");

        // login form, centered in the available part of the screen
        Component loginForm = buildLoginForm();

        // layout to center login form when there is sufficient screen space
        // - see the theme for how this is made responsive for various screen
        // sizes
        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setMargin(false);
        centeringLayout.setSpacing(false);
        centeringLayout.setStyleName("centering-layout");
        centeringLayout.addComponent(loginForm);
        centeringLayout.setComponentAlignment(loginForm,
                Alignment.MIDDLE_CENTER);

        // information text about logging in
        CssLayout loginInformation = buildLoginInformation();

        addComponent(centeringLayout);
        addComponent(loginInformation);
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.addStyleName("login-form");
        loginForm.setSizeUndefined();
        loginForm.setMargin(false);

        loginForm.addComponent(username = new TextField("Usuario", "admin"));
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField("Contraseña"));
        password.setWidth(15, Unit.EM);
        password.setDescription("Escribe cualquier cosa");
        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        loginForm.addComponent(buttons);

        buttons.addComponent(login = new Button("Login"));
        login.setDisableOnClick(true);
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    login();
                } finally {
                    login.setEnabled(true);
                }
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        //Linea boton de cambio de control
        buttons.addComponent(changeControl = new Button("Basico/Estricto"));
        changeControl.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                changeControl();
            }
        });

        buttons.addComponent(forgotPassword = new Button("Olvidaste tu contraseña?"));
        forgotPassword.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showNotification(new Notification("Pista: Prueba cualquier cosa"));
            }
        });
        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        return loginForm;
    }

    private CssLayout buildLoginInformation() {
        CssLayout loginInformation = new CssLayout();
        loginInformation.setStyleName("login-information");
        Label loginInfoText = new Label(
                "<h1>Información de inicio de Sesion</h1>"
                        + "Loggearse como &quot;admin&quot; para control total. No es necesaria una contraseña",
                ContentMode.HTML);
        loginInfoText.setSizeFull();
        loginInformation.addComponent(loginInfoText);
        return loginInformation;
    }

    private void login() {
        if (accessControl.signIn(username.getValue(), password.getValue())) {
            loginListener.loginSuccessful();
        } else {
            showNotification(new Notification("Credenciales Incorrectas",
                    "Porfavor revisa las credenciales e intenta nuevamente",
                    Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

    public AccessControl getAccessControl(){
        return  this.accessControl;
    }


    public interface LoginListener extends Serializable {
        void loginSuccessful();
    }

    private void changeControl(){
        if(this.control == MenuControl.basic){
            this.accessControl = new StrictAccesControl();
            this.control = MenuControl.strict;
            this.username.setValue("");
        }
        else{
            this.accessControl = new BasicAccessControl();
            this.control = MenuControl.basic;
            this.username.setValue("admin");
        }
    }

    public enum MenuControl{ basic, strict}
}
