package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.AccionPublicitariaService;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.ABMAccionView;
import com.PubliciBot.UI.Vistas.DetalleCampanaView;
import com.PubliciBot.UI.Vistas.SelectorTags;
import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import java.time.Instant;
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
    Label txtoduracion;

    Button btnGuardarCampana;
    Button btnVerCampanasGuardadas;
    Button seleccionarTags;
    Button crearCampana;
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

    Grid campanasList;
    Button btnGrilla = new Button("Ver Grilla");
    Upload uploadFile;

//comment

    public ABMCampanasController() {
        super();

        setSpacing(true);
        initComponents();
        dibujarControles();
        cargarComboDuracion();


        crearCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //datos de campaña
                String nombreCampana = txtNombreCampana.getValue();
                String descripcion = txtDescripcion.getValue();
                Date fechaCreacion = dfFechaInicio.getValue();
                int duracion = 0;
                if (!txtDuracion.getValue().equals(""))
                    duracion = Integer.parseInt(txtDuracion.getValue());
                UnidadMedida unidadMedida = obtenerUnidadMedida();
                String mensajeTxt = txtMensaje.getValue();
                Mensaje mensaje = null;

                //TODO le puse NULL al path de la imagen porque estaba haciendo un toString() de un objeto imagen
                if (imgImgenMensaje == null) {
                    mensaje = new Mensaje(mensajeTxt, null);
                }
                if (mensajeTxt == null || mensajeTxt.equals("")) {
                    mensaje = new Mensaje(null, null);
                } else
                    mensaje = new Mensaje(mensajeTxt, null);
                /*
                if (mensajeTxt == null || mensajeTxt.equals("")) {
                    mensaje = new Mensaje(null, imgImgenMensaje.toString());
                } else
                    mensaje = new Mensaje(mensajeTxt, imgImgenMensaje.toString());
                */
                boolean vacios = nombreCampana.equals("") || descripcion.equals("") || fechaCreacion.equals("") || duracion == 0 || unidadMedida == null || mensajeTxt.equals("") || mensaje == null;
                boolean txtVacio = txtDuracion.getValue().equals("") || txtDuracion == null || nombreCampana == null || descripcion == null || fechaCreacion == null;
                if (vacios || txtVacio) {
                    Notification.show("Capo, llena los campos antes plz");
                    return;
                }
                else {
                    nuevaCampana = new Campana(nombreCampana, descripcion, fechaCreacion, duracion*unidadMedida.unidadASegundos(), mensaje);
                }
            }
        });

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
                if (nuevaCampana != null) {
                    accionView.setModal(true);
                    UI.getCurrent().addWindow(accionView);
                } else {
                    Notification.show("Capo, primero agrega Campaña");
                }
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

        btnGrilla.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                cargarGrilla();
            }
        });


    }

    private boolean nuevaCampanaNoTieneTags() {
        return nuevaCampana.getTags().size() == 0;
    }


    private void cargarGrilla()
    {
        Usuario actual = getUsuarioSesion();
        if (actual != null) {
            campanasList.setContainerDataSource(new BeanItemContainer<>(
                    Campana.class, campanaService.findAll(actual)));
        }
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
        txtNombreCampana = new TextField("Nombre");
        txtNombreCampana.setValue("Campaña: " + Integer.toString(Date.from(Instant.now()).getDate()) + "/" + Integer.toString(Date.from(Instant.now()).getMonth())
                + ":" + Integer.toString(Date.from(Instant.now()).getSeconds()));
        txtDescripcion = new TextArea("Descripción");
        txtDescripcion.setValue("Creada el " + Date.from(Instant.now()).toString());
        dfFechaInicio = new DateField("Fecha de inicio");
        dfFechaInicio.setValue(Date.from(Instant.now()));
        txtDuracion = new TextField();
        txtDuracion.setValue("1");
        txtoduracion =new Label("Duración");
        cboUnidadTiempo = new ComboBox();
        cboUnidadTiempo.setWidth(115, Unit.PIXELS);

        txtMensaje = new TextArea("Mensaje adjunto");
        txtMensaje.setValue("Mensaje de Prueba");
        imgImgenMensaje = new Image("Imagen adjunta");
        crearCampana = new Button("Crear campaña");
        seleccionarTags = new Button("Seleccionar Tags");
        btnGuardarCampana = new Button("Guardar Campaña");
        btnVerCampanasGuardadas = new Button("Ver Campañas Guardadas");
        detalleCampanaSeleccionada = new Button("Detalles Campana");
        campanasGuardadas = new ListSelect("Campañas guardadas");
        campanasGuardadasList = new ArrayList<Campana>();
        hl = new HorizontalLayout();
        hl.setSpacing(true);

        btnAgregarAccion = new Button("Agregar Acción");
        btnEjecutarAcciones = new Button("Ejecutar Acciones");

        campanasList = new Grid();
        campanasList.setContainerDataSource(new BeanItemContainer<>(Campana.class));

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
        layoutcampana.addComponents(lblTitulo, txtNombreCampana, txtDescripcion, dfFechaInicio, txtoduracion);

        horizontalLayout.addComponents(txtDuracion, cboUnidadTiempo);
        horizontalLayout.setSpacing(true);
        layoutcampana.addComponents(horizontalLayout, txtMensaje, imgImgenMensaje, uploadFile);

        HorizontalLayout horizontalLayoutbotones = new HorizontalLayout();
        horizontalLayoutbotones.addComponents(crearCampana, seleccionarTags, btnAgregarAccion, btnGuardarCampana, btnVerCampanasGuardadas, btnGrilla);

        //horizontalLayoutbotones.addComponent(btnEjecutarAcciones);
        horizontalLayoutbotones.setSpacing(true);
        layoutcampana.addComponent(horizontalLayoutbotones);

        verticalLayout.addComponent(layoutcampana);
        this.addComponent(verticalLayout);

        hl.addComponents(campanasGuardadas, detalleCampanaSeleccionada, btnEjecutarAcciones);

        detalleCampanaSeleccionada.setVisible(false);
        btnEjecutarAcciones.setVisible(false);

        VerticalLayout verticalLayoutGrid = new VerticalLayout(campanasList);
        verticalLayoutGrid.setSizeFull();
        campanasList.setSizeFull();
    }
/*
    private void dibujarControles() {

        verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        VerticalLayout layoutcampana = new VerticalLayout();
        layoutcampana.setMargin(true);
        layoutcampana.setSpacing(true);
        layoutcampana.addComponent(lblTitulo);
        layoutcampana.addComponent(txtNombreCampana);
        layoutcampana.addComponent(txtDescripcion);
        layoutcampana.addComponent(dfFechaInicio);
        layoutcampana.addComponent(txtoduracion);
        horizontalLayout.addComponent(txtDuracion);
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


    public Button getBtnGuardarCampana() {
        return btnGuardarCampana;
    }
}