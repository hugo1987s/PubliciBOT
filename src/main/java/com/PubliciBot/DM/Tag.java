package com.PubliciBot.DM;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo on 14/05/2017.
 */
public class Tag {
    private String nombre;
    private Set<Tag> hijos;

    public Tag(String nombre) {
        this.nombre = nombre;
        this.hijos = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Tag> getHijos() {
        return hijos;
    }

    public void addHijo(Tag hijo)
    {
        this.hijos.add(hijo);
    }

    /*
    public void setHijos(Set<Tag> hijos) {
        this.hijos = hijos;
    }
    */
}
