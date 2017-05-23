package com.PubliciBot.Services;

import com.PubliciBot.DM.Rol;
import com.PubliciBot.DM.Usuario;

/**
 * Created by Hugo on 22/05/2017.
 */
public class UsuarioService {

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
        return null;
    }

    public void asignarRol(Usuario usuario, Rol rol)
    {
        usuario.setRol(rol);
    }
}
