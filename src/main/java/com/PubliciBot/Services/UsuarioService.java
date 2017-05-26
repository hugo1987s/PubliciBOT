package com.PubliciBot.Services;

import com.PubliciBot.DM.Privilegio;
import com.PubliciBot.DM.Rol;
import com.PubliciBot.DM.Usuario;
import com.PubliciBot.UI.ABMCampanas;
import com.PubliciBot.UI.ABMTags;

import java.util.ArrayList;

/**
 * Created by Hugo on 22/05/2017.
 */
public class UsuarioService {

    private ArrayList<Usuario> systemUsers;
    public UsuarioService(){
        systemUsers = new ArrayList<>();

        //USUARIO TECNICO
        Privilegio<ABMTags> privilegioTecnico = new Privilegio<>(ABMTags.class);
        Rol tecnico = new Rol("Tecnico");
        tecnico.add(privilegioTecnico);
        Usuario user = crearUsuario("IvoTecnico@megafono.com","soytecnico",tecnico);

        //USUARIO CLIENTE
        Privilegio<ABMCampanas> privilegioCliente = new Privilegio<>(ABMCampanas.class);
        Rol cliente = new Rol("Cliente");
        cliente.add(privilegioCliente);
        Usuario user2 = crearUsuario("IvoCliente@megafono.com","soycliente",cliente);

        this.systemUsers.add(user);
        this.systemUsers.add(user2);
    }

    public Usuario iniciarSesion(String email, String password)
    {
        return null;
    }

    public Usuario buscarUsuario(String emailUsuario)
    {
        return null;
    }

    public Usuario crearUsuario(String email, String password, Rol rol)
    {
        return new Usuario(email,password,rol);
    }

    public void asignarRol(Usuario usuario, Rol rol)
    {
        usuario.setRol(rol);
    }

    public ArrayList<Usuario> getSystemUsers(){
        return this.systemUsers;
    }
}
