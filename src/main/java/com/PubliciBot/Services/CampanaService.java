package com.PubliciBot.Services;

import com.PubliciBot.DM.*;

import java.util.Date;

/**
 * Created by Hugo on 22/05/2017.
 */
public class CampanaService {
    public void establecerEstado(Campana campana, EstadoCampana estadoCampana)
    {
        campana.setEstadoCampana(estadoCampana);
    }

    public void agregarTagACampana(Campana campana, Tag tag)
    {
        campana.addTags(tag);
    }

    public void agregarAccionPublicitariaACampana(Campana campana, AccionPublicitaria accion)
    {
        campana.addAccion(accion);
    }

    public void asignarDuracion(Campana campana, int dias)
    {
        campana.setDuracion(dias);
    }

    public Campana crearCampana(String nombre, String descripcion, Date fechaInicio, int duracion, Mensaje mensaje)
    {
        return new Campana(nombre, descripcion,fechaInicio,duracion,mensaje);
    }

}
