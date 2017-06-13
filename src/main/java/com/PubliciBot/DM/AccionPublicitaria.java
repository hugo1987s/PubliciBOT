package com.PubliciBot.DM;

/**
 * Created by Hugo on 14/05/2017.
 */
public class AccionPublicitaria {

    private String nombreAccion;
    private PeriodicidadAccion periodicidadAccion;
    private int valorPeriodicidad;
    private Medio medio;


    public AccionPublicitaria()
    {
        this.nombreAccion = "";
        this.periodicidadAccion = null;
        this.valorPeriodicidad = 0;
        this.medio = new Medio();
    }

    public AccionPublicitaria(String nombre, PeriodicidadAccion periodicidad, int valor, Medio medioAccion)
    {
        this.nombreAccion = nombre;
        this.periodicidadAccion = periodicidad;
        this.valorPeriodicidad = valor;
        this.medio = medioAccion;
    }


    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    public PeriodicidadAccion getPeriodicidadAccion() {
        return periodicidadAccion;
    }

    public void setPeriodicidadAccion(PeriodicidadAccion periodicidadAccion) {
        this.periodicidadAccion = periodicidadAccion;
    }

    public int getValorPeriodicidad() {
        return valorPeriodicidad;
    }

    public void setValorPeriodicidad(int valorPeriodicidad) {
        this.valorPeriodicidad = valorPeriodicidad;
    }

    public Medio getMedio() {
        return medio;
    }

    public void setMedio(Medio medio) {
        this.medio = medio;
    }
}
