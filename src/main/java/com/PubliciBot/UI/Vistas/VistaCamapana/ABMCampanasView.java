package com.PubliciBot.UI.Vistas.VistaCamapana;

import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Usuario;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.Controladores.ABMCampanasController;
import com.PubliciBot.UI.Vistas.Controladores.EstadisticasCampanaController;

import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/* User Interface written in Java.
 *
 * Define the user interface shown on the Vaadin generated web page by extending the UI class.
 * By default, a new UI instance is automatically created when the page is loaded. To reuse
 * the same instance, add @PreserveOnRefresh.
 */
@Title("Addressbook")
@Theme("valo")
public class ABMCampanasView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "Campañas";
    HorizontalLayout actions;

    public enum EstadoABMCampana{ NUEVACAMPANA, EDICIONCAMPANA}

    public ABMCampanasView (){
        super();
        configureComponents();
        buildLayout();
        //para que se pueda scrollear
        this.addStyleName("v-scrollable");
        this.setHeight("100%");
        //
        this.setMargin(true);
        VerticalLayout content = new VerticalLayout();
        // content has undefined height by default - just don't set one

       /* grillaCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                refreshCampanas("filtroTest");
            }
        });
        */
        refreshCampanas("filtroTest");
        this.setSpacing(false);
    }

    Grid campanasList = new Grid();
    CampanaService campanaService = new CampanaService();

    ABMCampanasController abmCampanasController = new ABMCampanasController(this);
    EstadisticasCampanaController estadisticasCampanaController ;
    Button grillaCampana = new Button("Ver campañas");
    Button nuevaCampana = new Button("+ Campaña");
    Button btnEditarCampaña = new Button("Editar");
    Campana seleccionada;
    EstadoABMCampana estadoABMCampana;


    /* Hundreds of widgets.
     * Vaadin's user interface components are just Java objects that encapsulate
     * and handle cross-browser support and client-server communication. The
     * default Vaadin components are in the com.vaadin.ui package and there
     * are over 500 more in vaadin.com/directory.
     */



    // ContactForm is an example of a custom component class



    /* The "Main method".
     *
     * This is the entry point method executed to initialize and configure
     * the visible user interface. Executed on every browser reload because
     * a new instance is created for each web page loaded.
     */

    private void configureComponents() {
         /* Synchronous event handling.
         *
         * Receive user interaction events on the server-side. This allows you
         * to synchronously handle those events. Vaadin automatically sends
         * only the needed changes to the web page without loading a new page.
         */

       //ADRESS BOOK







        // ABM CAMPAÑAS CONTROLLER
        abmCampanasController.setVisible(false);

        nuevaCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                campanasList.deselect(campanasList.getSelectedRow());
                estadoABMCampana =estadoABMCampana.NUEVACAMPANA;
                abmCampanasController.setVisible(true);
                abmCampanasController.crearCampana(new Campana());
                if(estadisticasCampanaController != null)
                    removeComponent(estadisticasCampanaController);
                actions.removeComponent(btnEditarCampaña);
            }
        });

       campanasList.addSelectionListener(new SelectionEvent.SelectionListener() {
           @Override
           public void select(SelectionEvent selectionEvent) {
               if(abmCampanasController.isVisible()){
                   campanasList.deselect(campanasList.getSelectedRow());
                   return;
               }

               if(seleccionada == null) {
                   seleccionada = (Campana) campanasList.getSelectedRow();
                   estadisticasCampanaController = new EstadisticasCampanaController(seleccionada);
                   addComponent(estadisticasCampanaController);
                   actions.addComponent(btnEditarCampaña);

               }
               else {
                   Campana seleccionadaGrid = (Campana) campanasList.getSelectedRow();
                   if(seleccionadaGrid != null) {
                       if(estadisticasCampanaController != null) {
                           removeComponent(estadisticasCampanaController);
                           actions.removeComponent(btnEditarCampaña);
                           seleccionada = seleccionadaGrid;
                           estadisticasCampanaController = new EstadisticasCampanaController(seleccionada);
                           addComponent(estadisticasCampanaController);
                           actions.addComponent(btnEditarCampaña);
                       }
                       else{
                           seleccionada = seleccionadaGrid;
                           estadisticasCampanaController = new EstadisticasCampanaController(seleccionada);
                           addComponent(estadisticasCampanaController);
                           actions.addComponent(btnEditarCampaña);
                       }
                   }
                   else{
                       removeComponent(estadisticasCampanaController);
                       actions.removeComponent(btnEditarCampaña);
                       estadisticasCampanaController = null;
                   }
               }
           }
       });

       btnEditarCampaña.addClickListener(new Button.ClickListener() {
           @Override
           public void buttonClick(Button.ClickEvent clickEvent) {
               if(estadisticasCampanaController.isVisible())
                   estadisticasCampanaController.setVisible(false);
               estadoABMCampana = estadoABMCampana.EDICIONCAMPANA;
               abmCampanasController.setVisible(true);
               seleccionada = (Campana)campanasList.getSelectedRow();
               abmCampanasController.crearCampana(seleccionada);
               campanasList.deselect(campanasList.getSelectedRow());
               removeComponent(estadisticasCampanaController);
               actions.removeComponent(btnEditarCampaña);
           }
       });
        campanasList.setContainerDataSource(new BeanItemContainer<>(Campana.class));

        campanasList.removeColumn("tags");
        campanasList.removeColumn("acciones");
        campanasList.removeColumn("mensaje");
        campanasList.removeColumn("id");


          campanasList.setColumnOrder("nombre","descripcion","estadoCampana","duracion","unidadMedida","fechaInicio");


    }

    /* Robust layouts.
     *
     * Layouts are components that contain other components.
     * HorizontalLayout contains TextField and Button. It is wrapped
     * with a Grid into VerticalLayout for the left side of the screen.
     * Allow user to resize the components with a SplitPanel.
     *
     * In addition to programmatically building layout in Java,
     * you may also choose to setup layout declaratively
     * with Vaadin Designer, CSS and HTML.
     */
    private void buildLayout() {
        /*/viejo
        HorizontalLayout actions = new HorizontalLayout(filter, newContact);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions,contactList);
        left.setSizeFull();
        contactList.setSizeFull();
        left.setExpandRatio(contactList, 1);


        HorizontalLayout mainLayout = new HorizontalLayout(left, contactForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);
       // FIN VIEJO*/

        //
        actions = new HorizontalLayout(nuevaCampana);
        actions.setSpacing(true);
        nuevaCampana.setStyleName(ValoTheme.BUTTON_PRIMARY);


        VerticalLayout left = new VerticalLayout(campanasList,actions);

        left.setSizeFull();
        campanasList.setSizeFull();
        left.setExpandRatio(campanasList, 1);
        Panel panel=new Panel();

        panel.setSizeFull();


        HorizontalLayout mainLayout = new HorizontalLayout(left,abmCampanasController);
        panel.setContent(mainLayout);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);
        mainLayout.setMargin(true);
        this.addComponent(panel);
        // Split and allow resizing
    }

    /* Choose the design patterns you like.
     *
     * It is good practice to have separate data access methods that
     * handle the back-end access and/or the user interface updates.
     * You can further split your code into classes to easier maintenance.
     * With Vaadin you can follow MVC, MVP or any other design pattern
     * you choose.
     */

public void addComponentScrollable(){

}

    public void refreshCampanas(String stringFilter) {
        campanaService.recuperarCampanas(getUsuarioSesion());
        campanasList.setContainerDataSource(new BeanItemContainer<>(
                Campana.class, campanaService.findAll()));

        //contactForm.setVisible(false);
    }

    private Usuario getUsuarioSesion() {
        StrictAccessControl strictAccessControl = (StrictAccessControl) (MyUI.get()).getAccessControl();
        if(strictAccessControl != null)
            return strictAccessControl.getRecoveredUser();

        return new Usuario();
    }

    public EstadoABMCampana getEstadoABMCampana(){
        return  this.estadoABMCampana;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    /*  Deployed as a Servlet or Portlet.
     *
     *  You can specify additional servlet parameters like the URI and UI
     *  class name and turn on production mode when you have finished developing the application.
     */

}
