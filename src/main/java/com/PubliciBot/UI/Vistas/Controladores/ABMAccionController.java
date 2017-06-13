package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.AccionPublicitaria;
import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.PeriodicidadAccion;
import com.PubliciBot.DM.TipoPost;
import com.PubliciBot.UI.Vistas.Validators.EnteroValidator;
import com.vaadin.ui.*;

/**
 * Created by Hugo on 10/06/2017.
 */
public class ABMAccionController extends VerticalLayout {

    TextField txtNombreAccion;
    TextField txtValorPeriodicidad;
    ComboBox cboPeriodicidad;
    ComboBox cboMedio;

    Panel panelMail;
    Panel panelRedes;

    TextField txtMail;
    TextField txtUsuarioOrigen;
    PasswordField txtPasswordOrigen;
    TextField txtCuentaDestino;

    Button btnAceptar;

    public ABMAccionController ()
    {
        super();
        initComponents();
        dibujarControles();

        cboMedio.addValueChangeListener(event -> {
                   if(cboMedio.getValue().toString().toUpperCase() == TipoPost.EMAIL.toString().toUpperCase())
                   {
                       panelRedes.setVisible(false);
                       panelMail.setVisible(true);
                   }
                   else
                   {
                       panelRedes.setVisible(true);
                       panelMail.setVisible(false);
                   }


                }
        );



        btnAceptar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                if(cboPeriodicidad.getValue() == null || cboPeriodicidad.getValue() == "") {
                    Notification.show("Debe seleccionar una periodicidad de posteo.");
                    cboPeriodicidad.focus();
                    return;
                }

                if(cboMedio.getValue() == null || cboMedio.getValue() == "") {
                    Notification.show("Debe seleccionar un medio de posteo.");
                    cboMedio.focus();
                    return;
                }


            }
        });
    }


    public AccionPublicitaria crearAccion()
    {
        AccionPublicitaria accion = new AccionPublicitaria();

        accion.setNombreAccion(this.txtNombreAccion.getValue());
        accion.setPeriodicidadAccion((PeriodicidadAccion) cboPeriodicidad.getValue());
        accion.setValorPeriodicidad(Integer.parseInt(txtValorPeriodicidad.getValue()));

        Medio medio = new Medio();
        medio.setTipoPost((TipoPost) cboMedio.getValue());

        if(medio.getTipoPost().equals(TipoPost.EMAIL))
            medio.setEmailDestino(txtMail.getValue());
        else
        {
            medio.setUsuarioPerfilOrigen(txtUsuarioOrigen.getValue());
            medio.setContrasenaPerfilOrigen(txtPasswordOrigen.getValue());
            medio.setPerfilDestino(txtCuentaDestino.getValue());
        }

        return accion;

    }


    private void initComponents()
    {
        txtNombreAccion = new TextField("Nombre");
        txtValorPeriodicidad = new TextField("Periodicicad");
        txtValorPeriodicidad.addValidator(new EnteroValidator());

        cboPeriodicidad = new ComboBox("Unidad de medida");
        cboPeriodicidad.addItems(PeriodicidadAccion.values());
        cboPeriodicidad.setNullSelectionAllowed(false);

        cboMedio = new ComboBox("Posteo en");
        cboMedio.addItems(TipoPost.values());
        cboMedio.setNullSelectionAllowed(false);

        txtMail = new TextField("Email destino");

        panelMail = new Panel();
        panelMail.setWidth("300");

        panelRedes = new Panel();
        panelRedes.setWidth("300");

        txtUsuarioOrigen = new TextField("Usuario");
        txtPasswordOrigen = new PasswordField("Contraseña");
        txtCuentaDestino = new TextField("Cuenta destino");

        btnAceptar = new Button("Aceptar");

    }

    private void dibujarControles()
    {
        FormLayout fl = new FormLayout();
        fl.addComponent(txtNombreAccion);
        fl.addComponent(txtValorPeriodicidad);
        fl.addComponent(cboPeriodicidad);
        fl.addComponent(cboMedio);

        FormLayout formLayoutMail = new FormLayout();
        formLayoutMail.addComponent(txtMail);
        panelMail.setContent(formLayoutMail);

        fl.addComponent(panelMail);


        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(txtUsuarioOrigen);
        formLayout.addComponent(txtPasswordOrigen);
        formLayout.addComponent(txtCuentaDestino);

        panelRedes.setContent(formLayout);

        fl.addComponent(panelRedes);

        fl.addComponent(btnAceptar);

        this.addComponent(fl);

        panelMail.setVisible(false);
        panelRedes.setVisible(false);
    }

}
