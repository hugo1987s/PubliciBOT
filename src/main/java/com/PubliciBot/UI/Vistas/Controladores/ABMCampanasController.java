package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.Services.UsuarioService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.ABMAccionView;
import com.PubliciBot.UI.Vistas.DetalleCampanaView;
import com.PubliciBot.UI.Vistas.SelectorTags;
import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hugo on 25/05/2017.
 */
public class ABMCampanasController extends HorizontalLayout {

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
    CampanaService campanaService;
    UsuarioService usuarioService;

    HorizontalLayout hl ;
    ListSelect campanasGuardadas;
    ArrayList<Campana> campanasGuardadasList;
    Button detalleCampanaSeleccionada;
    Campana campañaSeleccionada;

    Button btnAgregarAccion;
    VerticalLayout verticalLayout;

//comment

    public ABMCampanasController() {
        super();

        setSpacing(true);
        initComponents();
        dibujarControles();
        cargarComboDuracion();

        //SE ABRE VENTANA PARA ASIGNAR TAGS A CAMPAÑA
        btnGuardarCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                //datos de campaña
                String nombreCampana      = txtNombreCampana.getValue();
                String descripcion        = txtDescripcion.getValue();
                Date fechaCreacion        = dfFechaInicio.getValue();
                int duracion              = Integer.parseInt(txtDuracion.getValue());
                UnidadMedida unidadMedida = obtenerUnidadMedida();
                String mensajeTxt         = txtMensaje.getValue();
                Mensaje mensaje           = null;

                if(imgImgenMensaje == null){
                    mensaje = new Mensaje(mensajeTxt,null);
                }
                if(mensajeTxt == null || mensajeTxt.equals("")){
                    mensaje = new Mensaje(null,imgImgenMensaje.toString());
                }
                else
                    mensaje = new Mensaje(mensajeTxt,imgImgenMensaje.toString());

                //se obtiene el strictAccesControl para obtener al usuario actual de la sesion.
                Usuario actual       = getUsuarioSesion();
                Campana nuevaCampana = campanaService.crearCampana(nombreCampana,descripcion,fechaCreacion,duracion,unidadMedida,mensaje);


                SelectorTags tagger  = new SelectorTags();
                tagger.setModal(true);
                tagger.getSeleccionar().addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        ArrayList<Tag> tagsCampana = tagger.getSeleccionados();
                        for(Tag t : tagsCampana){
                            campanaService.agregarTagACampana(nuevaCampana,t);
                        }
                        usuarioService.agregarCampañaAUsuario(nuevaCampana,actual);
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
                getSelectedCampaign();
            }
        });


        detalleCampanaSeleccionada.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    DetalleCampanaView detalleCampañaView = new DetalleCampanaView(campañaSeleccionada);
                    detalleCampañaView.setModal(true);
                    UI.getCurrent().addWindow(detalleCampañaView);
                    // campanaService.agregarAccionPublicitariaACampana(nueva);
                }
        });

        btnAgregarAccion.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ABMAccionView accionView = new ABMAccionView();
                accionView.setModal(true);
                UI.getCurrent().addWindow(accionView);

            }
        });


    }

     private UnidadMedida obtenerUnidadMedida() {

        return (UnidadMedida) cboUnidadTiempo.getValue();
        /*UnidadMedida unidadMedida = null;
        if(unidad.equals("MES"))
            unidadMedida = UnidadMedida.MES;
        if(unidad.equals("SEMANA"))
            unidadMedida = UnidadMedida.SEMANA;
        if(unidad.equals("BIMESTRE"))
            unidadMedida = UnidadMedida.BIMESTRE;
        if(unidad.equals("SEMESTRE"))
            unidadMedida = UnidadMedida.SEMESTRE;
        return unidadMedida;
        */
    }

    private void initComponents() {

        campanaService             = new CampanaService();
        usuarioService             = new UsuarioService();
        lblTitulo                  = new Label("Administración de Campañas");

        txtNombreCampana           = new TextField("Nombre");
        txtDescripcion             = new TextArea("Descripción");
        dfFechaInicio              = new DateField("Fecha de inicio");
        txtDuracion                = new TextField("Duracion");
        cboUnidadTiempo            = new ComboBox("Unidad");
        txtMensaje                 = new TextArea("Mensaje adjunto");
        imgImgenMensaje            = new Image("Imagen adjunta");
        btnGuardarCampana          = new Button("Guardar");
        btnVerCampanasGuardadas    = new Button("Cargar");
        detalleCampanaSeleccionada = new Button("Detalles Campana");
        campanasGuardadas          = new ListSelect("Campañas guardadas");
        campanasGuardadasList      = new ArrayList<Campana>();
        hl                         = new HorizontalLayout();
        hl.setSpacing(true);

        btnAgregarAccion           = new Button("Agregar Acción");

    }

    private void dibujarControles() {

        verticalLayout = new VerticalLayout();
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
        formLayout.addComponent(btnAgregarAccion);

        verticalLayout.addComponent(formLayout);
        this.addComponent(verticalLayout);

        hl.addComponent(campanasGuardadas);
        hl.addComponent(detalleCampanaSeleccionada);
        detalleCampanaSeleccionada.setVisible(false);

    }

    private void cargarComboDuracion()
    {
        cboUnidadTiempo.addItems(UnidadMedida.values());
    }

    private void agregarListaCampanas(){
        Usuario actual = getUsuarioSesion();
        if(actual != null){
            campanaService.recuperarCampanas(actual);
            ArrayList<Campana> campanas = campanaService.getCampanasGuardadas();
            for(Campana camp : campanas) {
                    System.out.println(camp);
                    campanasGuardadas.addItem(camp.getNombre());
                    campanasGuardadasList.add(camp);

                }
            this.addComponent(hl);
        }
    }

    private Usuario getUsuarioSesion() {
        StrictAccessControl strictAccessControl = (StrictAccessControl) ((MyUI)getUI()).getAccessControl();
        return strictAccessControl.getRecoveredUser();
    }


    private void getSelectedCampaign() {
        campanasGuardadas.addValueChangeListener(event -> {// Java 8
            for (Campana c : campanasGuardadasList) {
                String nombre = c.getNombre();
                if (event.getProperty().getValue()!=null) {

                    String evento = event.getProperty().getValue().toString();
                    if (nombre.equals(evento)) {
                        campañaSeleccionada = c;
                        detalleCampanaSeleccionada.setVisible(true);
                    }
                }
                else {campanasGuardadasList.remove(c);

                }

            }

        });
    }

}
