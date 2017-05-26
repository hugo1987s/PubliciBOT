package com.PubliciBot.UI;

import com.PubliciBot.DM.DuracionCampana;
import com.vaadin.ui.*;

/**
 * Created by Hugo on 25/05/2017.
 */
public class ABMCampanasController extends VerticalLayout {

    Label lblTitulo;
    TextField txtNombreCampana;
    TextArea txtDescripcion;
    DateField dfFechaInicio;
    TextField txtDuracion;
    ComboBox cboUnidadTiempo;
    TextArea txtMensaje;
    Image imgImgenMensaje;
    Button btnGuardarCampana;


    public ABMCampanasController() {
        super();

        initComponents();
        dibujarControles();
        cargarComboDuracion();

    }

    private void initComponents() {

        lblTitulo = new Label("Administración de Campañas");

        txtNombreCampana = new TextField("Nombre");
        txtDescripcion = new TextArea("Descripción");
        dfFechaInicio = new DateField("Fecha de inicio");
        txtDuracion = new TextField("Duracion");
        cboUnidadTiempo = new ComboBox("Unidad");
        txtMensaje = new TextArea("Mensaje adjunto");
        imgImgenMensaje = new Image("Imagen adjunta");
        btnGuardarCampana = new Button("Guardar");
    }

    private void dibujarControles() {

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(lblTitulo);

        FormLayout formLayout = new FormLayout();

        formLayout.addComponent(txtNombreCampana);
        formLayout.addComponent(txtDescripcion);
        formLayout.addComponent(dfFechaInicio);
        formLayout.addComponent(txtDuracion);
        formLayout.addComponent(cboUnidadTiempo);
        formLayout.addComponent(txtMensaje);
        formLayout.addComponent(imgImgenMensaje);
        formLayout.addComponent(btnGuardarCampana);

        verticalLayout.addComponent(formLayout);

        this.addComponent(verticalLayout);
    }

    private void cargarComboDuracion()
    {

        cboUnidadTiempo.addItems(DuracionCampana.values());
    }
}
