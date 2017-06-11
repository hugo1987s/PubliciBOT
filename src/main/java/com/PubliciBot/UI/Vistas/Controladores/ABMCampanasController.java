package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.*;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.SelectorTags;
import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.data.Property;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
    CampanaService campanaService;

    HorizontalLayout hl ;
    ListSelect campanasGuardadas;
    ArrayList<Campana> campanasGuardadasList;
    Button detalleCampanaSeleccionada;
    Campana campañaSeleccionada;

    EstadisticasCampanaController detallesCampañaController;

//comment

    public ABMCampanasController() {
        super();

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
                Campana nuevaCampana = campanaService.crearCampana(nombreCampana,descripcion,fechaCreacion,duracion,unidadMedida,mensaje,actual);

                SelectorTags tagger  = new SelectorTags();
                tagger.setModal(true);
                tagger.getSeleccionar().addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        ArrayList<Tag> tagsCampana = tagger.getSeleccionados();
                        for(Tag t : tagsCampana){
                            campanaService.agregarTagACampana(nuevaCampana,t);
                        }
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
                if(detallesCampañaController == null) {
                    detallesCampañaController = new EstadisticasCampanaController(campañaSeleccionada);
                    hl.addComponent(detallesCampañaController);
                }
                else{
                    hl.removeComponent(detallesCampañaController);
                    detallesCampañaController = null;
                }
            }
        });


    }

    private UnidadMedida obtenerUnidadMedida() {
        String unidad = cboUnidadTiempo.getValue().toString();
        UnidadMedida unidadMedida = null;
        if(unidad.equals("MES"))
            unidadMedida = UnidadMedida.MES;
        if(unidad.equals("SEMANA"))
            unidadMedida = UnidadMedida.SEMANA;
        if(unidad.equals("BIMESTRE"))
            unidadMedida = UnidadMedida.BIMESTRE;
        if(unidad.equals("SEMESTRE"))
            unidadMedida = UnidadMedida.SEMESTRE;
        return unidadMedida;
    }

    private void initComponents() {

        campanaService             = new CampanaService();
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

        hl.addComponent(campanasGuardadas);
        hl.addComponent(detalleCampanaSeleccionada);
        detalleCampanaSeleccionada.setVisible(false);

    }

    private void cargarComboDuracion()
    {
        cboUnidadTiempo.addItems(DuracionCampana.values());
    }

    private void agregarListaCampanas(){
        Usuario actual = getUsuarioSesion();
        if(actual != null){
            campanaService.recuperarCampanas(actual);
            ArrayList<Campana> campanas = campanaService.getCampanasGuardadas();
            for(Campana camp : campanas) {
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

    private void getSelectedCampaign(){

        campanasGuardadas.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                for (Iterator i = campanasGuardadas.getItemIds().iterator(); i.hasNext();) {

                    Object iid = (Object) i.next();
                    if(campanasGuardadas.isSelected(iid)) {
                        for(Campana c : campanasGuardadasList){
                            if(c.getNombre().equals(iid.toString())) {
                                campañaSeleccionada = c;
                                detalleCampanaSeleccionada.setVisible(true);
                            }
                        }
                    }
                }
            }
        });

    }

}
