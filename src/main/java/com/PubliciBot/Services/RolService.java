package com.PubliciBot.Services;

import com.PubliciBot.DM.Privilegio;
import com.PubliciBot.DM.Rol;

import java.util.HashSet;

/**
 * Created by Hugo on 22/05/2017.
 */
public class RolService {
    public Rol crearRol(String nombreRol, HashSet<Privilegio> privilegios)
    {
        return new Rol(nombreRol, privilegios);
    }
}
