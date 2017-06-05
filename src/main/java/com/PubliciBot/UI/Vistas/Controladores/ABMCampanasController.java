package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.DuracionCampana;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.UI.Vistas.SelectorTags;
import com.PubliciBot.UI.authentication.CurrentUser;
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
    Button btnVerCampanasGuardadas;
    Button detalleCampanaSeleccionada;
    CampanaService campanaService;
    ListSelect campanasGuardadas;



    public ABMCampanasController() {
        super();

        initComponents();
        dibujarControles();
        cargarComboDuracion();

        btnGuardarCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SelectorTags tagger = new SelectorTags();
                tagger.setModal(true);
                UI.getCurrent().addWindow(tagger);

            }
        });


    }

    private void initComponents() {

        campanaService = new CampanaService();
        lblTitulo = new Label("Administración de Campañas");

        txtNombreCampana = new TextField("Nombre");
        txtDescripcion = new TextArea("Descripción");
        dfFechaInicio = new DateField("Fecha de inicio");
        txtDuracion = new TextField("Duracion");
        cboUnidadTiempo = new ComboBox("Unidad");
        txtMensaje = new TextArea("Mensaje adjunto");
        imgImgenMensaje = new Image("Imagen adjunta");
        btnGuardarCampana = new Button("Guardar");
        btnVerCampanasGuardadas = new Button("Cargar");
        detalleCampanaSeleccionada = new Button("Detalles Campana");
        campanasGuardadas = new ListSelect("Campañas guardadas");
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
        formLayout.addComponent(btnVerCampanasGuardadas);

        verticalLayout.addComponent(formLayout);

        this.addComponent(verticalLayout);

    }

    private void cargarComboDuracion()
    {
        cboUnidadTiempo.addItems(DuracionCampana.values());
    }

    private void agregarListaCampanas(){
        String currentUserMail = CurrentUser.get();

        //ArrayList<Campana> campanas = campanaService.recuperarCampanas(new Usuario);
        this.addComponent(campanasGuardadas);
    }

}
