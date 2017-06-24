package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.AccionPublicitariaService;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.Services.UsuarioService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.ABMAccionView;
import com.PubliciBot.UI.Vistas.DemoAddressBook.ABMCampanasView;
import com.PubliciBot.UI.Vistas.SelectorTags;
import com.PubliciBot.UI.Vistas.Validators.AccionView;
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
    ComboBox unidadMedida;
    TextArea txtMensaje;
    Image imgImgenMensaje;
    Label txtoduracion;

    Button btnGuardarCampana;

    Button seleccionarTags;
    CampanaService campanaService;

    AccionPublicitariaService publicitariaService;
    ABMAccionView accionView;

    HorizontalLayout hl;
    Button detalleCampanaSeleccionada;
    Campana nuevaCampana;

    Button btnAgregarAccion;
    VerticalLayout verticalLayout;

    Button btnEjecutarAcciones;

    Upload uploadFile;

    UsuarioService usuarioService = new UsuarioService();

    ABMCampanasView addressbookUIView;

    AccionView accionView2;

    BeanFieldGroup<Campana> formFieldBindings;


    Button cancelar;

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
                if(adbUI.getEstadoABMCampana() == ABMCampanasView.EstadoABMCampana.NUEVACAMPANA)
                    guardar();
               if(adbUI.getEstadoABMCampana() == ABMCampanasView.EstadoABMCampana.EDICIONCAMPANA)
                   guardarEdicion();
                setVisible(false);

                //MedioService medioService = new MedioService(nuevaCampana.getAcciones())
            }
        });

        cancelar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                setVisible(false);
            }
        });






    }

    private boolean nuevaCampanaNoTieneTags() {
        return nuevaCampana.getTags().size() == 0;
    }




    private UnidadMedida obtenerUnidadMedida() {

        return (UnidadMedida) unidadMedida.getValue();
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
        accionView2 = new AccionView(this);
        accionView = new ABMAccionView(accionView2);
        publicitariaService = new AccionPublicitariaService();
        nombre = new TextField("Nombre");
        descripcion = new TextArea("Descripción");
        fechaInicio = new DateField("Fecha de inicio");
        fechaInicio.setValue(Date.from(Instant.now()));
        duracion = new TextField();
        txtoduracion =new Label("Duración");
        unidadMedida = new ComboBox();
        unidadMedida.setWidth(115, Unit.PIXELS);

        txtMensaje = new TextArea("Mensaje adjunto");
        txtMensaje.setValue("Mensaje de Prueba");
       //imgImgenMensaje = new Image("Imagen adjunta");

        seleccionarTags = new Button("Seleccionar Tags");
        btnGuardarCampana = new Button("Guardar Campaña");

        detalleCampanaSeleccionada = new Button("Detalles Campana");
        cancelar = new Button("cancelar");

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

        horizontalLayout.addComponents(duracion, unidadMedida);
        horizontalLayout.setSpacing(true);
        layoutcampana.addComponents(horizontalLayout, txtMensaje, uploadFile);

        HorizontalLayout horizontalLayoutbotones = new HorizontalLayout();
        horizontalLayoutbotones.addComponents(seleccionarTags);

        //horizontalLayoutbotones.addComponent(btnEjecutarAcciones);
        horizontalLayoutbotones.setSpacing(true);
        layoutcampana.addComponent(horizontalLayoutbotones);
        layoutcampana.addComponent(btnAgregarAccion);
        layoutcampana.addComponent(btnGuardarCampana);
        layoutcampana.addComponent(cancelar);


        verticalLayout.addComponent(layoutcampana);
        this.addComponent(verticalLayout);

        hl.addComponents(detalleCampanaSeleccionada, btnEjecutarAcciones);

        detalleCampanaSeleccionada.setVisible(false);
        btnEjecutarAcciones.setVisible(false);

        VerticalLayout verticalLayoutGrid = new VerticalLayout();
        verticalLayoutGrid.setSizeFull();

    }

    private void cargarComboDuracion() {
        unidadMedida.addItems(UnidadMedida.values());
        unidadMedida.setValue(UnidadMedida.SEMANA);
    }

    private Usuario getUsuarioSesion() {
        StrictAccessControl strictAccessControl = (StrictAccessControl) ((MyUI) getUI()).getAccessControl();
        return strictAccessControl.getRecoveredUser();
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

    public void guardarEdicion(){
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();

            Usuario actual = getUsuarioSesion();
            usuarioService.guardarUsuario(actual);
            addressbookUIView.refreshCampanas("filtroTest");
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }

    public void guardar() {
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();

            //MENSAJE CAMPAÑA
            String mensajeTxt = txtMensaje.getValue();
            Mensaje mensaje = null;
            //TODO le puse NULL al path de la imagen porque estaba haciendo un toString() de un objeto imagen
            if (imgImgenMensaje == null) {
                mensaje = new Mensaje(mensajeTxt, null);
            }
            else if (mensajeTxt == null || mensajeTxt.equals("")) {
                mensaje = new Mensaje(null, null);
            }
            else if (mensajeTxt == null || mensajeTxt.equals("")) {
                mensaje = new Mensaje(null, imgImgenMensaje.toString());
            } else
                mensaje = new Mensaje(mensajeTxt, imgImgenMensaje.toString());
            nuevaCampana.setMensaje(mensaje);

            Usuario actual = getUsuarioSesion();
            if(!actual.getCampanas().contains(nuevaCampana)) {
                usuarioService.agregarCampañaAUsuario(nuevaCampana, actual);
                usuarioService.guardarUsuario(actual);
            }
            addressbookUIView.refreshCampanas("filtroTest");

        } catch (FieldGroup.CommitException e) {
            // Validation exceptions could be shown here
        }
    }

    public Button getBtnGuardarCampana() {
        return btnGuardarCampana;
    }
}