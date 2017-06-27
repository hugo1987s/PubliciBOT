package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.*;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.VistaCamapana.ABMAccionView;
import com.PubliciBot.UI.Vistas.VistaCamapana.ABMCampanasView;
import com.PubliciBot.UI.Vistas.VistaCamapana.SelectorTags;
import com.PubliciBot.UI.Vistas.VistaCamapana.AccionView;
import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hugo on 25/05/2017.
 */
public class ABMCampanasController extends HorizontalLayout {

    Label lblTitulo;

    TextField nombre;
    TextField descripcion;
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

    ArrayList<Campana> creadasEnSesion;

    Button btnAgregarAccion;
    VerticalLayout verticalLayout;

    Button btnEjecutarAcciones;

    Upload subirArchivo;

    UsuarioService usuarioService = new UsuarioService();

    ABMCampanasView addressbookUIView;

    AccionView accionView2;

    BeanFieldGroup<Campana> formFieldBindings;

    Button cancelar;

    String nombreArchivoGenerado;
//comment

    public ABMCampanasController(ABMCampanasView adbUI) {
        super();

        this.addressbookUIView = adbUI;
        setMargin(true);
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
                                nuevaCampana.getTags().clear();
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
                    accionView2.refreshAcciones(nuevaCampana);
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

        cancelar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                setVisible(false);
            }
        });
        this.addStyleName("v-scrollable");
        this.setHeight("100%");

        this.setSpacing(false);
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
        descripcion = new TextField("Descripción");
        fechaInicio = new DateField("Fecha de inicio");
        fechaInicio.setValue(Date.from(Instant.now()));
        duracion = new TextField();
        txtoduracion =new Label("Duración");
        unidadMedida = new ComboBox();
        unidadMedida.setWidth(115, Unit.PIXELS);

        txtMensaje = new TextArea("Mensaje adjunto");
        txtMensaje.setValue("Mensaje de Prueba");
        //imgImgenMensaje = new Image("Imagen adjunta");

        seleccionarTags = new Button("Tags");
        btnGuardarCampana = new Button(" Guardar ");

        //detalleCampanaSeleccionada = new Button("Detalles Campaña");
        cancelar = new Button("Cancelar");
        btnGuardarCampana.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnGuardarCampana.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        hl = new HorizontalLayout();


        btnAgregarAccion = new Button("Acciones");


         //UploadReceiver uploadReceiver = new UploadReceiver("src/main/resources/" + armarNombreArchivo());
        UploadReceiver uploadReceiver = new UploadReceiver(Utils.getProperty("path.imagenes") + armarNombreArchivo());

        subirArchivo = new Upload("Agregar Imagen...", uploadReceiver);
        subirArchivo.setButtonCaption("Subir");

        creadasEnSesion = new ArrayList<>();


        /*
        uploadFile = new Upload("Upload Image Here", receiver);

        uploadFile.setImmediate(true);
        uploadFile.setButtonCaption("Subir imagen");

        uploadFile.addSucceededListener(receiver);
*/
    }

    private String armarNombreArchivo()
    {
         this.nombreArchivoGenerado = Utils.generarNombreArchivoImagen();
        return this.nombreArchivoGenerado;
    }

    private void dibujarControles() {

        verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        VerticalLayout layoutcampana = new VerticalLayout();

        layoutcampana.setMargin(true);
        layoutcampana.setSpacing(true);

        layoutcampana.addComponents( nombre, descripcion, fechaInicio, txtoduracion);

        horizontalLayout.addComponents(duracion, unidadMedida);
        horizontalLayout.setSpacing(true);
        layoutcampana.addComponents(horizontalLayout, txtMensaje, subirArchivo);
        subirArchivo.setWidth(50,Unit.PERCENTAGE);

        seleccionarTags.setWidth(100,Unit.PERCENTAGE);
       // cancelar.setWidth(100,Unit.PERCENTAGE);
      //  btnGuardarCampana.setWidth(100,Unit.PERCENTAGE);
      //  btnAgregarAccion.setWidth(100,Unit.PERCENTAGE);
      //  seleccionarTags.setWidth(100,Unit.PERCENTAGE);

        VerticalLayout layoutcampanaEspaciada = new VerticalLayout();
        GridLayout grid = new GridLayout(2,2);


        layoutcampanaEspaciada.setSpacing(true);

        //layoutcampana.setSpacing(true);


        grid.setSpacing(true);


        layoutcampanaEspaciada.addComponent(grid);


        grid.addComponents(seleccionarTags);
        grid.addComponent(btnAgregarAccion);

        grid.addComponent(btnGuardarCampana);
        grid.addComponent(cancelar);

        layoutcampanaEspaciada.addComponent(grid);

        layoutcampana.addComponent(layoutcampanaEspaciada);


        verticalLayout.addComponent(layoutcampana);
        verticalLayout.setSpacing(true);
        //verticalLayout.addStyleName();
        this.addComponent(verticalLayout);

        this.setMargin(true);






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
        String mensajeCampana = campana.getMensaje().getTextoMensaje();
        txtMensaje.setValue(mensajeCampana);

        if(campana != null ){
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(campana, this);
            nombre.focus();
        }
    }


    public void guardar() {
        try {
            // Commit the fields from UI to DAO




            formFieldBindings.commit();

            //MENSAJE CAMPAÑA
            String mensajeTxt = txtMensaje.getValue();
            Mensaje mensaje = null;


			if(nombreArchivoGenerado!=null && nombreArchivoGenerado.trim() != "")
                mensaje = new Mensaje(mensajeTxt, Utils.getProperty("path.imagenes") + nombreArchivoGenerado,nuevaCampana.getDescripcion());
            else {

                   mensaje = new Mensaje(mensajeTxt, nombreArchivoGenerado,nuevaCampana.getDescripcion());

            }
            PostService PS=new PostService();
            nuevaCampana.setMensaje(mensaje);
            PS.generarPosts(nuevaCampana);
            nuevaCampana.actualizarEstado();

            //nuevaCampana.setEstadoCampana(EstadoCampana.PRELIMINAR);

            Usuario actual = getUsuarioSesion();
            usuarioService.agregarCampañaAUsuario(nuevaCampana,actual);

            usuarioService.guardarUsuario(actual);

            addressbookUIView.refreshCampanas();

        } catch (FieldGroup.CommitException e) {
            // Validation exceptions could be shown here
        }
    }

    public void eliminar(Campana seleccionada) {

        Usuario actual = getUsuarioSesion();


        actual.getCampanas().remove(seleccionada.getId());
        usuarioService.guardarUsuario(actual);
        for(Post post : seleccionada.getPosts()){
            Tasker.getTasker().removeTask(post);
            System.out.println("Tasker: Eliminando posts:"+post);
        }


        addressbookUIView.refreshCampanas();
    }
}