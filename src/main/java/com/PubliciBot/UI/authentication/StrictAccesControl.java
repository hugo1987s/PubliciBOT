package com.PubliciBot.UI.authentication;

import com.PubliciBot.DM.Privilegio;
import com.PubliciBot.DM.Rol;
import com.PubliciBot.DM.Usuario;
import com.PubliciBot.Services.UsuarioService;
import com.PubliciBot.UI.Vistas.ABMCampanasView;
import com.PubliciBot.UI.Vistas.ABMTagsView;

/**
 * Created by Max on 6/4/2017.
 */
public class StrictAccesControl implements AccessControl {

    private UsuarioService usuarioService ;
    private Usuario recoveredUser ;

    public StrictAccesControl(){
        usuarioService = new UsuarioService();
    }

    @Override
    public boolean signIn(String username, String password) {
        //descomentar las lineas de abajo para crear un usuario la primera vez que se ejecute, luego comentarla
        /*
        crearAdmin();
        crearCliente();
        crearTecnico();
        */
        recoveredUser = this.usuarioService.buscarUsuario(username,password);
       if(recoveredUser == null)
           return false;
       String recoveredName = recoveredUser.getMail();
       String recoveredPassword = recoveredUser.getContrasena();
       if(recoveredName.equals(username) && recoveredPassword.equals(password)){
           CurrentUser.set(username);
           CurrentUser.setPassword(password);
           return true;
       }
       return false;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        return recoveredUser.getRol().getDescripcion().equals(role);
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }


    //METODOS TEMPORALES ES PARA QUE CREEN UN USUARIO DE CADA ROL EN SUS DBS DESPUES SE ELIMINARAN
    private void crearTecnico(){
        Privilegio<ABMTagsView> tecnico = new Privilegio<>(ABMTagsView.class);
        Rol rolTecnico = new Rol("Tecnico");
        rolTecnico.add(tecnico);
        usuarioService.guardarUsuario( new Usuario("tecnico","tecnico",rolTecnico) );
    }

    private void crearCliente(){
        Privilegio<ABMCampanasView> cliente = new Privilegio<>(ABMCampanasView.class);
        Rol rolCliente = new Rol("Cliente");
        rolCliente.add(cliente);
        usuarioService.guardarUsuario( new Usuario("cliente","cliente",rolCliente) );
    }

    private void crearAdmin(){
        Privilegio<ABMTagsView> admin = new Privilegio<>(ABMTagsView.class);
        Privilegio<ABMTagsView> tecnico = new Privilegio<>(ABMTagsView.class);
        Privilegio<ABMCampanasView> cliente = new Privilegio<>(ABMCampanasView.class);
        Rol rolAdmin = new Rol("admin");
        rolAdmin.add(admin);
        rolAdmin.add(tecnico);
        rolAdmin.add(cliente);
        usuarioService.guardarUsuario( new Usuario("admin","admin",rolAdmin) );
    }

    public Usuario getRecoveredUser(){
        return this.recoveredUser;
    }
}
