package com.PubliciBot.DM;


import com.PubliciBot.DAO.Interfaces.Task;
import com.PubliciBot.Services.AccionPublicitariaService;

import java.time.Instant;
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
        APS.publicar(accion,mensaje);
        this.fechaUltimaEjecucion=Date.from(Instant.now());
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


}
