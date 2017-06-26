package com.PubliciBot.UI.Vistas.VistaCamapana;

import com.PubliciBot.DM.AccionPublicitaria;
import com.PubliciBot.DM.Campana;
import com.PubliciBot.UI.Vistas.Controladores.ABMAccionController;
import com.PubliciBot.UI.Vistas.Controladores.ABMCampanasController;
import com.PubliciBot.UI.Vistas.Controladores.EstadisticasCampanaController;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;

/**
 * Created by Max on 6/24/2017.
 */
public class AccionView extends VerticalLayout{


    public enum EstadoABMAccion{ NUEVAACCION, EDICIONACCION}

    Grid accionList = new Grid();
    ABMAccionController abmAccionController ;

    EstadisticasCampanaController estadisticasCampanaController ;
    Button nuevaAccion = new Button ("+ Accion");
    AccionPublicitaria seleccionada;
    Button eliminarAccion = new Button("Eliminar Accion");
    EstadoABMAccion estadoABMAccion;


    public AccionView(ABMCampanasController abmCampanasController){
        super();

        abmAccionController  = new ABMAccionController(this, abmCampanasController);
        abmAccionController.setVisible(false);
        configureComponents();
        buildLayout();
        //Scrolleable
        this.addStyleName("v-scrollable");
        this.setHeight("100%");


    }

    private void configureComponents() {


        nuevaAccion.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                abmAccionController.setVisible(true);
                abmAccionController.crearAccion(new AccionPublicitaria());
                accionList.deselect(accionList.getSelectedRow());
            }
        });

        //campanasList.setColumnOrder("nombre","descripcion");
        accionList.addSelectionListener(new SelectionEvent.SelectionListener() {
            @Override
            public void select(SelectionEvent selectionEvent) {
                if(seleccionada == null) {
                    seleccionada = (AccionPublicitaria) accionList.getSelectedRow();
                    addComponent(eliminarAccion);
                }
                else {
                    AccionPublicitaria seleccionadaGrid = (AccionPublicitaria) accionList.getSelectedRow();
                    if(seleccionadaGrid != null) {
                        if(estadisticasCampanaController != null) {
                            removeComponent(eliminarAccion);
                            seleccionada = seleccionadaGrid;
                            addComponent(eliminarAccion);
                        }
                        else{
                            seleccionada = seleccionadaGrid;
                            addComponent(eliminarAccion);
                        }
                    }
                    else{
                        removeComponent(eliminarAccion);
                    }
                }
            }
        });

        eliminarAccion.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                abmAccionController.eliminarAccion((AccionPublicitaria)accionList.getSelectedRow());
                accionList.deselect(accionList.getSelectedRow());
            }
        });
        accionList.setContainerDataSource(new BeanItemContainer<>(AccionPublicitaria.class));

    }

    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(nuevaAccion);

        VerticalLayout left = new VerticalLayout(actions,accionList);

        left.setSizeFull();
        accionList.setSizeFull();
        left.setExpandRatio(accionList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, abmAccionController);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        this.addComponent(mainLayout);
        // Split and allow resizing
    }


    public void refreshAcciones(Campana camp) {
        ArrayList<AccionPublicitaria> acciones = camp.getAcciones();
        accionList.setContainerDataSource(new BeanItemContainer<>(
               AccionPublicitaria.class, acciones));
    }

    public EstadoABMAccion getEstadoABMAccion(){
        return estadoABMAccion ;
    }
}
