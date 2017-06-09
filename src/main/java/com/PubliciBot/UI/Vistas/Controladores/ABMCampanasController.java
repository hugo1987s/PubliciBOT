package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.SelectorTags;
import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Date;

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

//comment

    public ABMCampanasController() {
        super();

        initComponents();
        dibujarControles();
        cargarComboDuracion();

        btnGuardarCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String nombreCampana = txtNombreCampana.getValue();
                String descripcion = txtDescripcion.getValue();
                Date fechaCreacion = dfFechaInicio.getValue();
                int duracion = Integer.parseInt(txtDuracion.getValue());
                String mensajeTxt = txtMensaje.getValue();
                Mensaje mensaje = null;
                if(imgImgenMensaje == null){
                    mensaje = new Mensaje(mensajeTxt);
                }
                if(mensajeTxt == null || mensajeTxt.equals("")){
                    mensaje = new Mensaje(imgImgenMensaje);
                }
                else
                    mensaje = new Mensaje(mensajeTxt,imgImgenMensaje);


                StrictAccessControl strictAccessControl = (StrictAccessControl) ((MyUI)getUI()).getAccessControl();
                Usuario actual = strictAccessControl.getRecoveredUser();

                Campana nuevaCampana = new Campana(nombreCampana,descripcion,fechaCreacion,duracion,mensaje,actual);
                System.out.println(nuevaCampana);

                SelectorTags tagger = new SelectorTags();
                tagger.setModal(true);
                tagger.getSeleccionar().addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        ArrayList<Tag> tagsCampana = tagger.getSeleccionados();
                        for(Tag t : tagsCampana){
                            campanaService.agregarTagACampana(nuevaCampana,t);
                        }
                        //TODO revisar por que se guarda todos los objetos del proyecto
                        campanaService.guardarCampana(nuevaCampana);
                        tagger.vaciarSeleccionados();
                    }
                });
                tagger.getCerrar().addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        tagger.close();
                        tagger.vaciarSeleccionados();
                    }
                });
                UI.getCurrent().addWindow(tagger);

            }
        });

        btnVerCampanasGuardadas.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                agregarListaCampanas();
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
        StrictAccessControl strictAccessControl = (StrictAccessControl) ((MyUI)getUI()).getAccessControl();
        Usuario actual = strictAccessControl.getRecoveredUser();
        if(actual != null){
            campanaService.recuperarCampanas(actual);
            ArrayList<Campana> campanas = campanaService.getCampanasGuardadas();
            for(Campana camp : campanas)
                campanasGuardadas.addItem(camp);
            this.addComponent(campanasGuardadas);
            this.addComponent(detalleCampanaSeleccionada);
        }
    }

}
