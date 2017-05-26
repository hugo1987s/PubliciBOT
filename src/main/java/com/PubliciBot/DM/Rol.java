package com.PubliciBot.DM;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo on 14/05/2017.
 */
public class Rol {
    private String descripcion;
    private Set<Privilegio> listaPrivilegios;

    public Rol() {
        this.descripcion = "";
        this.listaPrivilegios = new HashSet<>() ;
    }

    public Rol(String descripcion) {
        this.descripcion = descripcion;
        this.listaPrivilegios = new HashSet<>() ;
    }

    public Rol(String descripcion, Set<Privilegio> listaPrivilegios) {
        this.descripcion = descripcion;
        this.listaPrivilegios = listaPrivilegios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Privilegio> getListaPrivilegios() {
        return listaPrivilegios;
    }

    public void add(Privilegio privilegios) {
        this.listaPrivilegios.add(privilegios);
    }

    public void setListaPrivilegios(Set<Privilegio> listaPrivilegios) {
        this.listaPrivilegios = listaPrivilegios;
    }
}
