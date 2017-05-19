package com.PubliciBot.DM;

import java.util.HashSet;
import java.util.Set;

    /**
     * Created by Hugo on 14/05/2017.
     */
    public class Tag {
        private String nombre;
    private Set<AccionPublicitaria> acciones;


    public Tag(String nombre) {
            this.nombre = nombre;
            this.acciones = new HashSet<>();
    }




    public String getNombre() {
        return nombre;
    }

    public Set<AccionPublicitaria> getAcciones() {
        return acciones;
    }

    public void setAcciones(Set<AccionPublicitaria> acciones) {
        this.acciones = acciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return this.nombre;
    }
}
