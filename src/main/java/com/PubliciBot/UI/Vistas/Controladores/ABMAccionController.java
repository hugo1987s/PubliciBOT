package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.PeriodicidadAccion;
import com.PubliciBot.DM.TipoPost;
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

        cboPeriodicidad.addValueChangeListener(valueChangeEvent -> {
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

    private void initComponents()
    {
        txtNombreAccion = new TextField("Nombre");
        txtValorPeriodicidad = new TextField("Periodicicad");
        cboPeriodicidad = new ComboBox("Unidad de medida");
        cboPeriodicidad.addItems(PeriodicidadAccion.values());

        cboMedio = new ComboBox("Posteo en");
        cboMedio.addItems(TipoPost.values());

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

        fl.addComponent(formLayoutMail);


        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(txtUsuarioOrigen);
        formLayout.addComponent(txtPasswordOrigen);
        formLayout.addComponent(txtCuentaDestino);

        panelRedes.setContent(formLayout);

        fl.addComponent(panelRedes);

        fl.addComponent(btnAceptar);

        this.addComponent(fl);

    }

}
