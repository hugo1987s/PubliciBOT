package com.PubliciBot.DM;

/**
 * Created by Hugo on 14/05/2017.
 */
public class AccionPublicitaria {

    private String nombreAccion;
    private int periodicidadSegundos;
    private Medio medio;
    private String destino;


    public AccionPublicitaria()
    {
        this.nombreAccion = "";
        this.periodicidadSegundos = 60;
        this.medio = new Medio();
    }

    public int getPeriodicidadSegundos() {
        return periodicidadSegundos;
    }

    public void setPeriodicidadSegundos(int periodicidadSegundos) {
        this.periodicidadSegundos = periodicidadSegundos;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public AccionPublicitaria(String nombre, int periodicidadSegundos, Medio medioAccion, String destino)
    {
        this.nombreAccion = nombre;
        this.destino=destino;
        this.periodicidadSegundos =periodicidadSegundos;
        this.medio = medioAccion;

    }


    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }


    public int getValorPeriodicidad() {
        return periodicidadSegundos;
    }

    public void setValorPeriodicidad(int valorPeriodicidad) {
        this.periodicidadSegundos = valorPeriodicidad;
    }

    public Medio getMedio() {
        return medio;
    }

    public void setMedio(Medio medio) {
        this.medio = medio;
    }


}
