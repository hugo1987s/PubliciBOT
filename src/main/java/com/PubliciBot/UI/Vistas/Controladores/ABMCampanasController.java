package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.AccionPublicitariaService;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.Services.UsuarioService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.ABMAccionView;
import com.PubliciBot.UI.Vistas.DemoAddressBook.ABMCampanasView;
import com.PubliciBot.UI.Vistas.DetalleCampanaView;
import com.PubliciBot.UI.Vistas.SelectorTags;
import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hugo on 25/05/2017.
 */
public class ABMCampanasController extends HorizontalLayout {

    Label lblTitulo;

    TextField nombre;
    TextArea descripcion;
    DateField fechaInicio;
    TextField duracion;
    ComboBox cboUnidadTiempo;
    TextArea txtMensaje;
    Image imgImgenMensaje;
    Label txtoduracion;

    Button btnGuardarCampana;

    Button seleccionarTags;
    CampanaService campanaService;

    AccionPublicitariaService publicitariaService;
    ABMAccionView accionView;

    HorizontalLayout hl;
    ListSelect campanasGuardadas;
    ArrayList<Campana> campanasGuardadasList;
    Button detalleCampanaSeleccionada;
    Campana campañaSeleccionada;
    Campana nuevaCampana;

    Button btnAgregarAccion;
    VerticalLayout verticalLayout;

    Button btnEjecutarAcciones;


    Upload uploadFile;

    UsuarioService usuarioService = new UsuarioService();

    ABMCampanasView addressbookUIView;
    BeanFieldGroup<Campana> formFieldBindings;

//comment

    public ABMCampanasController(ABMCampanasView adbUI) {
        super();

        this.addressbookUIView = adbUI;
        setSpacing(true);
        initComponents();
        dibujarControles();
        cargarComboDuracion();




        //SE ABRE VENTANA PARA ASIGNAR TAGS A CAMPAÑA
        seleccionarTags.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(nuevaCampana == null)
                    Notification.show("primero se debe crear la campaña");
                else {
                    SelectorTags tagger = new SelectorTags();
                    tagger.setModal(true);
                    tagger.getSeleccionar().addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent clickEvent) {
                            ArrayList<Tag> tagsCampana = tagger.getSeleccionados();
                            for (Tag t : tagsCampana) {
                                campanaService.agregarTagACampana(nuevaCampana, t);
                            }
                            tagger.vaciarSeleccionados();
                            tagger.close();
                            Notification.show("Campanas agregadas: " + nuevaCampana.getTags());
                        }
                    });
                    tagger.getCerrar().addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent clickEvent) {
                            tagger.close();
                            tagger.vaciarSeleccionados();
                            if (nuevaCampanaNoTieneTags()) {
                                Notification.show("No se seleccionaron tags");
                            }
                        }
                    });
                    UI.getCurrent().addWindow(tagger);
                }
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
                if (nuevaCampana != null) {
                    accionView.setModal(true);
                    UI.getCurrent().addWindow(accionView);
                } else {
                    Notification.show("Capo, primero agrega Campaña");
                }
            }
        });


        btnGuardarCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
               guardar();
               setVisible(false);

                //MedioService medioService = new MedioService(nuevaCampana.getAcciones())
            }
        });


        btnEjecutarAcciones.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                if(campañaSeleccionada!=null)
                {
                    for(AccionPublicitaria accion : campañaSeleccionada.getAcciones()  )
                    {
                        if(accion.getMedio().getTipoMedio().equals(TipoMedio.EMAIL))
                        {
                            AccionPublicitariaService aps=new AccionPublicitariaService();
                            aps.publicar(accion, nuevaCampana.getMensaje());

                        }
                    }
                }
            }
        });




    }

    private boolean nuevaCampanaNoTieneTags() {
        return nuevaCampana.getTags().size() == 0;
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

        campanaService = new CampanaService();

        lblTitulo = new Label("Administración de Campañas");
        accionView = new ABMAccionView(this);
        publicitariaService = new AccionPublicitariaService();
        nombre = new TextField("Nombre");
        nombre.setValue("Campaña: " + Integer.toString(Date.from(Instant.now()).getDate()) + "/" + Integer.toString(Date.from(Instant.now()).getMonth())
                + ":" + Integer.toString(Date.from(Instant.now()).getSeconds()));
        descripcion = new TextArea("Descripción");
        descripcion.setValue("Creada el " + Date.from(Instant.now()).toString());
        fechaInicio = new DateField("Fecha de inicio");
        fechaInicio.setValue(Date.from(Instant.now()));
        duracion = new TextField();
        duracion.setValue("1");
        txtoduracion =new Label("Duración");
        cboUnidadTiempo = new ComboBox();
        cboUnidadTiempo.setWidth(115, Unit.PIXELS);

        txtMensaje = new TextArea("Mensaje adjunto");
        txtMensaje.setValue("Mensaje de Prueba");
        imgImgenMensaje = new Image("Imagen adjunta");

        seleccionarTags = new Button("Seleccionar Tags");
        btnGuardarCampana = new Button("Guardar Campaña");

        detalleCampanaSeleccionada = new Button("Detalles Campana");
        campanasGuardadas = new ListSelect("Campañas guardadas");
        campanasGuardadasList = new ArrayList<Campana>();
        hl = new HorizontalLayout();
        hl.setSpacing(true);

        btnAgregarAccion = new Button("Agregar Acción");
        btnEjecutarAcciones = new Button("Ejecutar Acciones");
        UploadReceiver receiver = new UploadReceiver();

// Create the upload with a caption and set receiver later
        uploadFile = new Upload("Upload Image Here", receiver);

        uploadFile.setImmediate(true);
        uploadFile.setButtonCaption("Subir imagen");

        uploadFile.addSucceededListener(receiver);

    }


    private void dibujarControles() {

        verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        VerticalLayout layoutcampana = new VerticalLayout();
        layoutcampana.setMargin(true);
        layoutcampana.setSpacing(true);
        layoutcampana.addComponents(lblTitulo, nombre, descripcion, fechaInicio, txtoduracion);

        horizontalLayout.addComponents(duracion, cboUnidadTiempo);
        horizontalLayout.setSpacing(true);
        layoutcampana.addComponents(horizontalLayout, txtMensaje, imgImgenMensaje, uploadFile);

        HorizontalLayout horizontalLayoutbotones = new HorizontalLayout();
        horizontalLayoutbotones.addComponents(seleccionarTags, btnAgregarAccion, btnGuardarCampana);

        //horizontalLayoutbotones.addComponent(btnEjecutarAcciones);
        horizontalLayoutbotones.setSpacing(true);
        layoutcampana.addComponent(horizontalLayoutbotones);

        verticalLayout.addComponent(layoutcampana);
        this.addComponent(verticalLayout);

        hl.addComponents(campanasGuardadas, detalleCampanaSeleccionada, btnEjecutarAcciones);

        detalleCampanaSeleccionada.setVisible(false);
        btnEjecutarAcciones.setVisible(false);

        VerticalLayout verticalLayoutGrid = new VerticalLayout();
        verticalLayoutGrid.setSizeFull();

    }
/*
    private void dibujarControles() {

        verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        VerticalLayout layoutcampana = new VerticalLayout();
        layoutcampana.setMargin(true);
        layoutcampana.setSpacing(true);
        layoutcampana.addComponent(lblTitulo);
        layoutcampana.addComponent(nombre);
        layoutcampana.addComponent(descripcion);
        layoutcampana.addComponent(fechaInicio);
        layoutcampana.addComponent(txtoduracion);
        horizontalLayout.addComponent(duracion);
        horizontalLayout.addComponent(cboUnidadTiempo);
        horizontalLayout.setSpacing(true);
        layoutcampana.addComponent(horizontalLayout);
        layoutcampana.addComponent(txtMensaje);
        layoutcampana.addComponent(imgImgenMensaje);

        HorizontalLayout horizontalLayoutbotones = new HorizontalLayout();

        horizontalLayoutbotones.addComponent(crearCampana);
        horizontalLayoutbotones.addComponent(seleccionarTags);
        horizontalLayoutbotones.addComponent(btnAgregarAccion);
        horizontalLayoutbotones.addComponent(btnGuardarCampana);
        horizontalLayoutbotones.addComponent(btnVerCampanasGuardadas);
        //horizontalLayoutbotones.addComponent(btnEjecutarAcciones);
        horizontalLayoutbotones.setSpacing(true);
        layoutcampana.addComponent(horizontalLayoutbotones);

        verticalLayout.addComponent(layoutcampana);
        this.addComponent(verticalLayout);

        hl.addComponent(campanasGuardadas);
        hl.addComponent(detalleCampanaSeleccionada);
        hl.addComponent(btnEjecutarAcciones);
        detalleCampanaSeleccionada.setVisible(false);
        btnEjecutarAcciones.setVisible(false);

    }
*/
    private void cargarComboDuracion() {
        cboUnidadTiempo.addItems(UnidadMedida.values());
        cboUnidadTiempo.setValue(UnidadMedida.SEMANA);
    }

    private void agregarListaCampanas() {
        this.removeComponent(hl);
        Usuario actual = getUsuarioSesion();
        if (actual != null) {
            campanaService.recuperarCampanas(actual);
            ArrayList<Campana> campanas = campanaService.getCampanasGuardadas();
            for (Campana camp : campanas) {
                campanasGuardadas.addItem(camp.getNombre());
                campanasGuardadasList.add(camp);
            }
            this.addComponent(hl);
        }
    }

    private Usuario getUsuarioSesion() {
        StrictAccessControl strictAccessControl = (StrictAccessControl) ((MyUI) getUI()).getAccessControl();
        return strictAccessControl.getRecoveredUser();
    }


    private void getSelectedCampaign() {
        campanasGuardadas.addValueChangeListener(event -> {// Java 8
            for (Campana c : campanasGuardadasList) {
                String nombre = c.getNombre();
                if (event.getProperty().getValue() != null) {

                    String evento = event.getProperty().getValue().toString();
                    if (nombre.equals(evento)) {
                        campañaSeleccionada = c;
                        detalleCampanaSeleccionada.setVisible(true);
                        btnEjecutarAcciones.setVisible(true);
                    }
                } else {
                    campanasGuardadasList.remove(c);
                }
            }
        });
    }

    public AccionPublicitariaService getPublicitariaService() {
        return this.publicitariaService;
    }

    public Campana getNuevaCampana() {
        return this.nuevaCampana;
    }

    public void crearCampana(Campana campana){
        this.nuevaCampana = campana;
        if(campana != null ){
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(campana, this);
            nombre.focus();
        }
    }

    public void guardar() {
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();

            String msg = String.format("Saved '%s %s'.",
                    nuevaCampana.getNombre(),
                    nuevaCampana.getDescripcion());
            Notification.show(msg, Notification.Type.TRAY_NOTIFICATION);

            Usuario actual = getUsuarioSesion();
            usuarioService.agregarCampañaAUsuario(nuevaCampana, actual);
            usuarioService.guardarUsuario(actual);
            addressbookUIView.refreshCampanas("filtroTest");

        } catch (FieldGroup.CommitException e) {
            // Validation exceptions could be shown here
        }
    }

    public Button getBtnGuardarCampana() {
        return btnGuardarCampana;
    }
}