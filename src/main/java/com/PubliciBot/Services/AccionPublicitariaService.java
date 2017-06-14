package com.PubliciBot.Services;

import com.PubliciBot.DM.AccionPublicitaria;
import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.PeriodicidadAccion;

/**
 * Created by Max on 6/13/2017.
 */
public class AccionPublicitariaService {

    private AccionPublicitaria accionPublicitaria;

    public AccionPublicitariaService(){

    }

    public void crearAccion(String nombre, PeriodicidadAccion periodicidad, int valor, Medio medioAccion){
        this.accionPublicitaria = new AccionPublicitaria( nombre, periodicidad, valor, medioAccion);
    }

    public AccionPublicitaria getAccionPublicitaria(){
        return this.accionPublicitaria;
    }


}
