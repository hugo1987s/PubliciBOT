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
//comment
public class StrictAccessControl implements AccessControl {

    private UsuarioService usuarioService ;
    private Usuario recoveredUser;
    private Usuario admin ;

    public StrictAccessControl(){
        usuarioService = new UsuarioService();
        admin = crearAdmin();
    }

    @Override
    public boolean signIn(String username, String password) {
        crearTecnico();
        crearCliente();
        if(username.equals("admin") && password.equals("admin")){
            this.recoveredUser = admin;
            return true;
        }
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
        Usuario tecnicoUser = new Usuario("tecnico","tecnico",rolTecnico);
        Usuario recup = usuarioService.buscarUsuario("tecnico","tecnico");
        if(recup == null || !recup.equals(tecnicoUser))
            usuarioService.guardarUsuario(tecnicoUser);
    }

    private void crearCliente(){
        Privilegio<ABMCampanasView> cliente = new Privilegio<>(ABMCampanasView.class);
        Rol rolCliente = new Rol("Cliente");
        rolCliente.add(cliente);
        Usuario clientUser =  new Usuario("cliente","cliente",rolCliente);
        Usuario recup = usuarioService.buscarUsuario("cliente","cliente");
        if(recup == null || !recup.equals(clientUser))
            usuarioService.guardarUsuario(clientUser);
    }

    private Usuario crearAdmin(){
        Privilegio<ABMTagsView> admin = new Privilegio<>(ABMTagsView.class);
        Privilegio<ABMTagsView> tecnico = new Privilegio<>(ABMTagsView.class);
        Privilegio<ABMCampanasView> cliente = new Privilegio<>(ABMCampanasView.class);
        Rol rolAdmin = new Rol("admin");
        rolAdmin.add(admin);
        rolAdmin.add(tecnico);
        rolAdmin.add(cliente);
        Usuario retAdmin = new Usuario("adimn","admin",rolAdmin);
        return retAdmin;
    }

    public Usuario getRecoveredUser(){
        return this.recoveredUser;
    }
}
