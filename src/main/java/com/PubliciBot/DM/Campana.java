package com.PubliciBot.DM;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hugo on 14/05/2017.
 */
public class Campana {
    private String nombre;
    private String descripcion;
    private int duracion;
    private Date fechaInicio;
    private Mensaje mensaje;
    private ArrayList<Tag> tags;
    private ArrayList<AccionPublicitaria> acciones;
    private EstadoCampana estadoCampana;
    private Usuario usuario;

    public Campana(String nombre, String descripcion, Date fechaInicio, int duracion, Mensaje mensaje, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.estadoCampana = EstadoCampana.PRELIMINAR;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.tags = new ArrayList<Tag>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void addTags(Tag tag) {
        this.tags.add(tag);
    }

    public ArrayList<AccionPublicitaria> getAcciones() {
        return acciones;
    }

    public void addAccion(AccionPublicitaria accion) {
        this.acciones.add(accion);
    }

    public EstadoCampana getEstadoCampana() {
        return estadoCampana;
    }

    public void setEstadoCampana(EstadoCampana estadoCampana) {
        this.estadoCampana = estadoCampana;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString(){
        return "Nombre Campaña "+this.nombre+"\n"+
                "Descripcion "+ this.descripcion+"\n"+
                "Duracion "+ this.duracion+"\n"+
                "Mensaje "+this.mensaje.toString()+"\n"+
                "Inicio "+ this.fechaInicio.toString()+"\n"+
                "Estado "+ this.estadoCampana.toString()+"\n"+
                "Usuario "+this.usuario.toString()+"\n"+
                "Tags "+ this.tags+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campana campana = (Campana) o;

        if (duracion != campana.duracion) return false;
        if (nombre != null ? !nombre.equals(campana.nombre) : campana.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(campana.descripcion) : campana.descripcion != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(campana.fechaInicio) : campana.fechaInicio != null) return false;
        if (mensaje != null ? !mensaje.equals(campana.mensaje) : campana.mensaje != null) return false;
        if (tags != null ? !tags.equals(campana.tags) : campana.tags != null) return false;
        if (acciones != null ? !acciones.equals(campana.acciones) : campana.acciones != null) return false;
        return usuario != null ? usuario.equals(campana.usuario) : campana.usuario == null;
    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + duracion;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (mensaje != null ? mensaje.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (acciones != null ? acciones.hashCode() : 0);
        result = 31 * result + (estadoCampana != null ? estadoCampana.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }
}
