package com.PubliciBot.UI.Vistas.Controladores;

import com.PubliciBot.DM.AccionPublicitaria;
import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Tag;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;

/**
 * Created by Max on 6/4/2017.
 */
public class EstadisticasCampanaController extends VerticalLayout {

    private ArrayList<Label> tagsCampana;
    private ArrayList<Label> accionesCampana;
    private Image imagenCampana;
    private Label tituloCampana;
    private Label descripcionCampana;
    private Label textoCampana;
    private Label duracionCampana;
    private Label fechaInicioCampana;
    private Label accionCampana;


    public EstadisticasCampanaController(Campana campana){
            initComponents(campana);
            this.setMargin(true);
            addComponents();
    }

    private void initComponents(Campana campana){
        this.tagsCampana = new ArrayList<>();
        this.accionesCampana = new ArrayList<>();
        this.agregarLabels(campana);
        this.tituloCampana      = new Label("Nombre "  + campana.getNombre());
        this.descripcionCampana = new Label("Info "    + campana.getDescripcion());
        this.textoCampana       = new Label("Mensaje " + campana.getMensaje().getTextoMensaje());
        this.duracionCampana    = new Label("Duracion" + campana.getDuracion()+ " "+ campana.getUnidadMedida());
        this.fechaInicioCampana = new Label("Inicio"   + campana.getFechaInicio());
        this.accionCampana      = new Label ("Acciones ");
    }

    private void agregarLabels(Campana campana){
        Label lbl = null;
        for(Tag tag : campana.getTags()){
            lbl = new Label("#"+tag.getNombre());
            tagsCampana.add(lbl);
        }
        for(AccionPublicitaria ac : campana.getAcciones()){
            lbl = new Label ("accion: " + ac.getNombreAccion() + " medio "+ ac.getMedio().toString());
            accionesCampana.add(lbl);
        }
    }

    private void addComponents(){
        addComponent(tituloCampana);
        addComponent(descripcionCampana);
        addComponent(textoCampana);
        addComponent(duracionCampana);
        addComponent(fechaInicioCampana);
        for(Label lbl : tagsCampana)
            addComponent(lbl);
        addComponent(accionCampana);
        for(Label label: accionesCampana)
            addComponent(label);
        this.setMargin(true);
        this.setSpacing(true);

    }






}
