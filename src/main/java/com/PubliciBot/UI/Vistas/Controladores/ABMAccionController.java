package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.AccionPublicitariaService;
import com.PubliciBot.UI.Vistas.Validators.AccionView;
import com.PubliciBot.UI.Vistas.Validators.EnteroValidator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;

/**
 * Created by Hugo on 10/06/2017.
 */
public class ABMAccionController extends HorizontalLayout {

    TextField nombreAccion;
    TextField destino;
    TextField periodicidadSegundos;

    ComboBox cboPeriodicidad;
    ComboBox cboMedio;

    Panel panelMail;
    Panel panelRedes;

    TextField txtUsuarioOrigen;
    PasswordField txtPasswordOrigen;
    TextField txtCuentaDestino;

    Button btnAceptar;
    AccionPublicitaria nuevaAccion;

    BeanFieldGroup<AccionPublicitaria> formFieldBindings;

    private AccionPublicitariaService publicitariaService;

    ABMCampanasController abmCampanasController;
    AccionView accionView;

    Button cancelar;


    public ABMAccionController(AccionView accionView, ABMCampanasController controller) {
        super();
        initComponents();
        dibujarControles();
        abmCampanasController = controller;
        this.accionView = accionView;

        cboMedio.addValueChangeListener(event -> {
            if (cboMedio.getValue().toString().toUpperCase() == TipoMedio.EMAIL.toString().toUpperCase()) {
                panelRedes.setVisible(false);
                panelMail.setVisible(true);
            } else {
                panelRedes.setVisible(true);
                panelMail.setVisible(false);
            }
                }
        );

        btnAceptar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

/*
                if (cboPeriodicidad.getValue() == null || cboPeriodicidad.getValue() == "") {
                    Notification.show("Debe seleccionar una periodicidad de posteo.");
                    cboPeriodicidad.focus();
                    return;
                }

                if (medio.getValue() == null || medio.getValue() == "") {
                    Notification.show("Debe seleccionar un medio de posteo.");
                    medio.focus();
                    return;
                }*/

                //controller.getPublicitariaService().setAccionPublicitaria(crearAccion());
                //AccionPublicitaria ac = controller.getPublicitariaService().getAccionPublicitaria();

                guardarAccion();
                setVisible(false);
                    /*
                    abmCampanasController.getNuevaCampana().addAccion(crearAccion());
                    Collection<Window> views = ((MyUI) getUI()).getWindows();
                    for(Window w : views)
                        if(w instanceof  ABMAccionView) {
                            close(w);
                        }
                        */
            }
        });

        cancelar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                setVisible(false);
            }
        });
    }

    private void close(Window view) {
        view.close();
    }


    private void agregarMedio() {

        PeriodicidadAccion periodicidadAccion = (PeriodicidadAccion) cboPeriodicidad.getValue();

        nuevaAccion.setPeriodicidadSegundos(nuevaAccion.getPeriodicidadSegundos() * periodicidadAccion.periodicidadASegundos());

        Medio medio = new Medio();
        medio.setTipoMedio((TipoMedio) this.cboMedio.getValue());

        //if (medio.getTipoMedio().equals(TipoMedio.EMAIL))
         //   nuevaAccion.setDestino(destino.getValue());

            medio.setUsuarioPerfilOrigen(txtUsuarioOrigen.getValue());
            medio.setContrasenaPerfilOrigen(txtPasswordOrigen.getValue());
            medio.setPerfilDestino(txtCuentaDestino.getValue());

        nuevaAccion.setMedio(medio);
    }


    private void initComponents() {
        publicitariaService = new AccionPublicitariaService();
        nombreAccion = new TextField("Nombre");
        periodicidadSegundos = new TextField("Periodicidad");
        periodicidadSegundos.addValidator(new EnteroValidator());

        cboPeriodicidad = new ComboBox("Unidad de medida");
        cboPeriodicidad.addItems(PeriodicidadAccion.values());
        cboPeriodicidad.setNullSelectionAllowed(false);

        cboMedio = new ComboBox("Posteo en");
        cboMedio.addItems(TipoMedio.values());
        cboMedio.setNullSelectionAllowed(false);

        destino = new TextField("Email destino");

        panelMail = new Panel();
        panelMail.setWidth("300");

        panelRedes = new Panel();
        panelRedes.setWidth("300");

        txtUsuarioOrigen = new TextField("Usuario");
        txtPasswordOrigen = new PasswordField("Contraseña");
        txtCuentaDestino = new TextField("Cuenta destino");

        btnAceptar = new Button("Aceptar");
        cancelar = new Button ("cancelar");

    }

    private void dibujarControles() {
        FormLayout fl = new FormLayout();
        fl.addComponent(nombreAccion);
        fl.addComponent(periodicidadSegundos);
        fl.addComponent(cboPeriodicidad);
        fl.addComponent(cboMedio);

        FormLayout formLayoutMail = new FormLayout();
        formLayoutMail.addComponent(destino);
        panelMail.setContent(formLayoutMail);

        fl.addComponent(panelMail);


        FormLayout formLayout = new FormLayout();
        formLayout.addComponent(txtUsuarioOrigen);
        formLayout.addComponent(txtPasswordOrigen);
        formLayout.addComponent(txtCuentaDestino);

        panelRedes.setContent(formLayout);

        fl.addComponent(panelRedes);

        fl.addComponent(btnAceptar);
        fl.addComponent(cancelar);

        this.addComponent(fl);

        panelMail.setVisible(false);
        panelRedes.setVisible(false);
    }


    public void crearAccion(AccionPublicitaria accion) {
        this.nuevaAccion = accion;
        if (accion != null) {
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(accion, this);
        }
    }


    public void guardarAccion() {
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();
            agregarMedio();
            Campana actual = abmCampanasController.getNuevaCampana();

            actual.addAccion(nuevaAccion);
            accionView.refreshAcciones(actual);

        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }

}
