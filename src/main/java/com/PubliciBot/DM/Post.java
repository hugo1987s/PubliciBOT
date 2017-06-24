package com.PubliciBot.DM;


import com.PubliciBot.DAO.Interfaces.Task;
import com.PubliciBot.Services.AccionPublicitariaService;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Post implements Task{
    private Date fechaUltimaEjecucion;
    private Date fechaCaducidad;
    private Date fechaInicio;
    private AccionPublicitaria accion;
    private Mensaje mensaje;



    @Override
    public void execute() {
        AccionPublicitariaService APS=new AccionPublicitariaService();
       boolean enviado= APS.publicar(accion,mensaje);
        enviado=true; //testing
       if(enviado) {
           this.fechaUltimaEjecucion = Date.from(Instant.now());

           Calendar c = Calendar.getInstance();
           c.add(Calendar.SECOND, this.accion.getPeriodicidadSegundos());
           System.out.println("Post: Enviado!");
           System.out.println("Post: Se ejecutara de nuevo el: " + c.getTime() + " Osea dentro de " + this.accion.getPeriodicidadSegundos() + " Segundos");
       }
       else{
           System.out.println("Error de envio");
       }


    }




    public AccionPublicitaria getAccion() {
        return accion;
    }

    public void setAccion(AccionPublicitaria accion) {
        this.accion = accion;
    }

    public Date getFechaInicio() {

        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Post (Date fechaInicio, Date fechaCaducidad, AccionPublicitaria accion, Mensaje mensaje ){
        this.fechaCaducidad=fechaCaducidad;
        this.fechaInicio=fechaInicio;
        this.accion=accion;
        this.mensaje=mensaje;
    }


    public Date getFechaUltimaEjecucion() {
        return fechaUltimaEjecucion;
    }

    public void setFechaUltimaEjecucion(Date fechaUltimaEjecucion) {
        this.fechaUltimaEjecucion = fechaUltimaEjecucion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (fechaCaducidad != null ? !fechaCaducidad.equals(post.fechaCaducidad) : post.fechaCaducidad != null)
            return false;
        if (fechaInicio != null ? !fechaInicio.equals(post.fechaInicio) : post.fechaInicio != null) return false;
        if (accion != null ? !accion.equals(post.accion) : post.accion != null) return false;
        return mensaje != null ? mensaje.equals(post.mensaje) : post.mensaje == null;
    }

    @Override
<<<<<<< HEAD
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (fechaUltimaEjecucion != null ? !fechaUltimaEjecucion.equals(post.fechaUltimaEjecucion) : post.fechaUltimaEjecucion != null)
            return false;
        if (fechaCaducidad != null ? !fechaCaducidad.equals(post.fechaCaducidad) : post.fechaCaducidad != null)
            return false;
        if (fechaInicio != null ? !fechaInicio.equals(post.fechaInicio) : post.fechaInicio != null) return false;
        if (accion != null ? !accion.equals(post.accion) : post.accion != null) return false;
        return mensaje != null ? mensaje.equals(post.mensaje) : post.mensaje == null;
    }

    @Override
    public int hashCode() {
        int result = fechaUltimaEjecucion != null ? fechaUltimaEjecucion.hashCode() : 0;
        result = 31 * result + (fechaCaducidad != null ? fechaCaducidad.hashCode() : 0);
=======
    public int hashCode() {
        int result = fechaCaducidad != null ? fechaCaducidad.hashCode() : 0;
>>>>>>> f53264fd867f3927b1bfeb114297c0e2beada3f8
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (accion != null ? accion.hashCode() : 0);
        result = 31 * result + (mensaje != null ? mensaje.hashCode() : 0);
        return result;
    }
}
