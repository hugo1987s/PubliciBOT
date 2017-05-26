package com.PubliciBot.UI;

import com.vaadin.ui.*;

/**
 * Created by Hugo on 25/05/2017.
 */
public class ABMCampanasController extends VerticalLayout {

    public ABMCampanasController()
    {
        super();

        Label lblTitulo = new Label("Administración de Campañas");
        TextField txtNombreCampana = new TextField("Nombre");
        TextArea txtDescripcion = new TextArea("Descripción");
        DateField dfFechaInicio = new DateField("Fecha de inicio");
        TextField txtDuracion = new TextField("Duracion");
        ComboBox cboUnidadTiempo = new ComboBox("Unidad");
        TextArea txtMensaje = new TextArea("Mensaje adjunto");
        Image imgImgenMensaje = new Image("Imagen adjunta");

        Button btnGuardarCampana = new Button("Guardar");


        FormLayout formLayout = new FormLayout();

        formLayout.addComponent(txtNombreCampana);
        formLayout.addComponent(txtDescripcion);
        formLayout.addComponent(dfFechaInicio);
        formLayout.addComponent(txtDuracion);
        formLayout.addComponent(cboUnidadTiempo);
        formLayout.addComponent(txtMensaje);
        formLayout.addComponent(imgImgenMensaje);
        formLayout.addComponent(btnGuardarCampana);

        this.addComponent(formLayout);


    }
}
