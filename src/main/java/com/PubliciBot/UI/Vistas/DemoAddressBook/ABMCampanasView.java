package com.PubliciBot.UI.Vistas.DemoAddressBook;

import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Usuario;
import com.PubliciBot.Services.CampanaService;
import com.PubliciBot.UI.MyUI;
import com.PubliciBot.UI.Vistas.Controladores.ABMCampanasController;
import com.PubliciBot.UI.Vistas.DemoAddressBook.Backend.Contact;
import com.PubliciBot.UI.Vistas.DemoAddressBook.Backend.ContactService;
import com.PubliciBot.UI.authentication.StrictAccessControl;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

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


    public ABMCampanasView (){
        super();
        configureComponents();
        buildLayout();

       /* grillaCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                refreshCampanas("filtroTest");
            }
        });
        */
        refreshCampanas("filtroTest");
    }



    /* Hundreds of widgets.
     * Vaadin's user interface components are just Java objects that encapsulate
     * and handle cross-browser support and client-server communication. The
     * default Vaadin components are in the com.vaadin.ui package and there
     * are over 500 more in vaadin.com/directory.
     */
    TextField filter = new TextField();
    Grid contactList = new Grid();
    Button newContact = new Button("New contact");

    // ContactForm is an example of a custom component class
    ContactForm contactForm = new ContactForm(this);

    // ContactService is a in-memory mock DAO that mimics
    // a real-world datasource. Typically implemented for
    // example as EJB or Spring Data based service.
    ContactService service = ContactService.createDemoService();

    Grid campanasList = new Grid();
    CampanaService campanaService = new CampanaService();

    ABMCampanasController abmCampanasController = new ABMCampanasController(this);
    Button grillaCampana = new Button("Ver campañas");
    Button nuevaCampana = new Button("Nueva Campaña");

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
        newContact.addClickListener(e -> contactForm.edit(new Contact()));

        filter.setInputPrompt("Filter contacts...");
        filter.addTextChangeListener(e -> refreshContacts(e.getText()));

        contactList.setContainerDataSource(new BeanItemContainer<>(Contact.class));
        contactList.setColumnOrder("firstName", "lastName", "email");
        contactList.removeColumn("id");
        contactList.removeColumn("birthDate");
        contactList.removeColumn("phone");
        contactList.setSelectionMode(Grid.SelectionMode.SINGLE);
        contactList.addSelectionListener(e
                -> contactForm.edit((Contact) contactList.getSelectedRow()));
        refreshContacts();

        // ABM CAMPAÑAS CONTROLLER
        abmCampanasController.setVisible(false);
        addComponent(abmCampanasController);

        nuevaCampana.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                abmCampanasController.setVisible(true);
                abmCampanasController.crearCampana(new Campana());
            }
        });
        campanasList.setColumnOrder("nombre","descripcion");
        campanasList.setContainerDataSource(new BeanItemContainer<>(Campana.class));

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
        HorizontalLayout actions = new HorizontalLayout(nuevaCampana);

        VerticalLayout left = new VerticalLayout(actions,campanasList);

        left.setSizeFull();
        campanasList.setSizeFull();
        left.setExpandRatio(campanasList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, abmCampanasController);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);
//*/
        this.addComponent(mainLayout);
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
    void refreshContacts() {
        refreshContacts(filter.getValue());
    }

    private void refreshContacts(String stringFilter) {
        contactList.setContainerDataSource(new BeanItemContainer<>(
                Contact.class, service.findAll(stringFilter)));
        contactForm.setVisible(false);
    }

    public void refreshCampanas(String stringFilter) {
        campanaService.recuperarCampanas(getUsuarioSesion());
        campanasList.setContainerDataSource(new BeanItemContainer<>(
                Campana.class, campanaService.getCampanasGuardadas()));
        //contactForm.setVisible(false);
    }

    private Usuario getUsuarioSesion() {
        StrictAccessControl strictAccessControl = (StrictAccessControl) (MyUI.get()).getAccessControl();
        if(strictAccessControl != null)
            return strictAccessControl.getRecoveredUser();

        return new Usuario();
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
