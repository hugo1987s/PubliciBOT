package com.PubliciBot.DM;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hugo on 14/05/2017.
 */
public class Campana implements Serializable{

    @Size(min=2, max=10, message="El nombre debe tener entre 2 y 10 caracteres.")
    @javax.validation.constraints.NotNull
    private String nombre;

    @Size(min=10, max=100, message="La descripcion debe tener entre 2 y 10 caracteres.")
    @javax.validation.constraints.NotNull
    private String descripcion;

    @Min(1)
    private int duracion;

    @javax.validation.constraints.NotNull
    private UnidadMedida unidadMedida;

    @javax.validation.constraints.NotNull
    private Date fechaInicio;

    @javax.validation.constraints.NotNull
    private Mensaje mensaje;

    @Size(min = 1, message =  "La campaña debe tener al menos un tag")
    private ArrayList<Tag> tags;

    private ArrayList<AccionPublicitaria> acciones;
    private ArrayList<Post> posts;

    @javax.validation.constraints.NotNull
    private EstadoCampana estadoCampana;


    public Campana(String nombre, String descripcion, Date fechaInicio, int duracion, UnidadMedida unidadMedida, Mensaje mensaje ) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.unidadMedida = unidadMedida;
        this.estadoCampana = EstadoCampana.PRELIMINAR;
        this.mensaje = mensaje;
        this.tags = new ArrayList<Tag>();
        this.acciones = new ArrayList<>();

        posts=new ArrayList<Post>();

    }

    public Campana(){
        this.fechaInicio = new Date(0);
        this.duracion = 0;
        this.unidadMedida = UnidadMedida.SEMANA;
        this.estadoCampana = EstadoCampana.PRELIMINAR;
        this.mensaje = new Mensaje();
        this.tags = new ArrayList<Tag>();
        this.acciones = new ArrayList<>();

        posts=new ArrayList<Post>();
    }

    public Date calcularCaducidad(){
      Instant instant= Instant.ofEpochSecond(fechaInicio.toInstant().getEpochSecond()+duracion);
     return Date.from(instant);
    }


    public void agregarPost(AccionPublicitaria accion){
        Post post=new Post(this.fechaInicio,calcularCaducidad(),accion,mensaje);
        this.posts.add(post);
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
        agregarPost(accion);
    }

    public EstadoCampana getEstadoCampana() {
        return estadoCampana;
    }

    public void setEstadoCampana(EstadoCampana estadoCampana) {
        this.estadoCampana = estadoCampana;
    }

    public UnidadMedida getUnidadMedida() { return unidadMedida; }

    public void setUnidadMedida(UnidadMedida unidadMedida) { this.unidadMedida = unidadMedida; }

    @Override
    public String toString(){
        return "Nombre Campaña "+this.nombre+"\n"+
                "Descripcion "+ this.descripcion+"\n"+
                "Duracion "+ this.duracion+"\n"+
                "Mensaje "+this.mensaje.toString()+"\n"+
                "Inicio "+ this.fechaInicio.toString()+"\n"+
                "Estado "+ this.estadoCampana.toString()+"\n"+
                "Tags "+ this.tags+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campana campana = (Campana) o;

        if (duracion != campana.duracion) return false;
        if (unidadMedida != null ? !unidadMedida.equals(campana.unidadMedida) : campana.unidadMedida != null) return false;
        if (nombre != null ? !nombre.equals(campana.nombre) : campana.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(campana.descripcion) : campana.descripcion != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(campana.fechaInicio) : campana.fechaInicio != null) return false;
        if (mensaje != null ? !mensaje.equals(campana.mensaje) : campana.mensaje != null) return false;
        if (tags != null ? !tags.equals(campana.tags) : campana.tags != null) return false;
        return  acciones != null ? !acciones.equals(campana.acciones) : campana.acciones != null;

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
        return result;
    }
}
