package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.AccionPublicitariaService;
import com.PubliciBot.Services.PostService;
import com.PubliciBot.UI.Vistas.Validators.EnteroValidator;
import com.PubliciBot.UI.Vistas.VistaCamapana.AccionView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

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
                this.getUI().showNotification("Aun no implementado, por el momento solo Email");
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

private void clearValidators(){
        nombreAccion.removeAllValidators();
        destino.removeAllValidators();
        periodicidadSegundos.removeAllValidators();
}

    private void validateFields(){
        nombreAccion.addValidator(
                new StringLengthValidator(
                        "Must be between 2 and 10 characters in length", 2, 10, false));
        //TODO FALTARIA AGREGAR MAIL
        destino.addValidator(
                new StringLengthValidator(
                        "Debe estar entre 10 y 20 caracteres", 10,20,false));
        periodicidadSegundos.addValidator(
                new IntegerRangeValidator("Como minimo 1", 1, Integer.MAX_VALUE ));
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
        periodicidadSegundos.setValue("1");
        cboPeriodicidad = new ComboBox("Unidad de medida");
        cboPeriodicidad.addItems(PeriodicidadAccion.values());
        cboPeriodicidad.setNullSelectionAllowed(false);
        cboPeriodicidad.setValue(PeriodicidadAccion.MES);


        cboMedio = new ComboBox("Posteo en");
        cboMedio.addItems(TipoMedio.values());
        cboMedio.setNullSelectionAllowed(false);
        cboMedio.setValue(TipoMedio.EMAIL);


        destino = new TextField("Email destino");

        panelMail = new Panel();
        panelMail.setWidth("300");

        panelRedes = new Panel();
        panelRedes.setWidth("300");

        txtUsuarioOrigen = new TextField("Usuario");
        txtPasswordOrigen = new PasswordField("Contraseña");
        txtCuentaDestino = new TextField("Cuenta destino");
        btnAceptar = new Button("Aceptar");
        cancelar = new Button ("Cancelar");
        btnAceptar.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnAceptar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
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
        this.setSpacing(true);
        this.setMargin(true);
        panelMail.setVisible(true);
        panelRedes.setVisible(false);
    }


    public void crearAccion(AccionPublicitaria accion) {
        validateFields();
        this.nuevaAccion = accion;
        if (accion != null) {
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(accion, this);
        }
    }


    public void guardarAccion() {
        try {
            boolean areValid = formFieldBindings.isValid();
            if(areValid) {
                // Commit the fields from UI to DAO
                formFieldBindings.commit();
                agregarMedio();
                Campana actual = abmCampanasController.getNuevaCampana();

                actual.addAccion(nuevaAccion);
                accionView.refreshAcciones(actual);
                setVisible(false);
            }
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAccion(AccionPublicitaria selectedRow) {
        Campana actual = abmCampanasController.getNuevaCampana();
        PostService PS=new PostService();
        PS.eliminarPosts(actual,selectedRow);
        actual.removeAccion(selectedRow);
        accionView.refreshAcciones(actual);
    }
}
