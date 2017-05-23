package com.PubliciBot.Services;

import com.PubliciBot.DM.Privilegios;
import com.PubliciBot.DM.Rol;


import java.util.Set;

/**
 * Created by Hugo on 22/05/2017.
 */
public class RolService {
    public Rol crearRol(String nombreRol, Set<Privilegios> privilegios)
    {
        return new Rol(nombreRol, privilegios);
    }
}
