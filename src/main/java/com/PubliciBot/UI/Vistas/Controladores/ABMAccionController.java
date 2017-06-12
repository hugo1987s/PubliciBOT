package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.PeriodicidadAccion;
import com.vaadin.ui.*;

/**
 * Created by Hugo on 10/06/2017.
 */
public class ABMAccionController extends VerticalLayout {

    TextField txtNombreAccion;
    TextField txtValorPeriodicidad;
    ComboBox cboPeriodicidad;

    Panel panelMail;
    Panel panelRedes;

    TextField txtMail;
    TextField txtUsuarioOrigen;
    PasswordField txtPasswordOrigen;
    TextField txtCuentaDestino;

    public ABMAccionController ()
    {
        super();
        initComponents();
        dibujarControles();

        cboPeriodicidad.addValueChangeListener(valueChangeEvent -> {
                    //Lanzar evento cuando cambia el valor del combo

                }
        );
    }

    private void initComponents()
    {
        txtNombreAccion = new TextField("Nombre");
        txtValorPeriodicidad = new TextField("Periodicicad");
        cboPeriodicidad = new ComboBox("Unidad de medida");

        txtMail = new TextField("Email destino");

        panelMail = new Panel();
        panelMail.setContent(txtMail);

        txtUsuarioOrigen = new TextField("Usuario");
        txtPasswordOrigen = new PasswordField("Contraseña");
        txtCuentaDestino = new TextField("Cuenta destino");

    }

    private void dibujarControles()
    {
        FormLayout fl = new FormLayout();
        fl.addComponent(txtNombreAccion);
        fl.addComponent(txtValorPeriodicidad);
        fl.addComponent(cboPeriodicidad);
        fl.addComponent(panelMail);


        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(txtUsuarioOrigen);
        formLayout.addComponent(txtPasswordOrigen);
        formLayout.addComponent(txtCuentaDestino);

        panelRedes.setContent(formLayout);

        fl.addComponent(panelRedes);

        this.addComponent(fl);

    }

}
