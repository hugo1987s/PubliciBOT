package com.PubliciBot.DM;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo on 14/05/2017.
 */
public class Rol {
    private String descripcion;
    private Set<Recurso> listaRecursos;

    public Rol() {
        this.descripcion = "";
        this.listaRecursos = new HashSet<>() ;
    }

    public Rol(String descripcion) {
        this.descripcion = descripcion;
        this.listaRecursos = new HashSet<>() ;
    }

    public Rol(String descripcion, Set<Recurso> listaRecursos) {
        this.descripcion = descripcion;
        this.listaRecursos = listaRecursos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void add(Recurso recurso) {
        this.listaRecursos.add(recurso);
    }

    public void setListaRecursos(Set<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }
}
