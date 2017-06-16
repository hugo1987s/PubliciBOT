package com.PubliciBot.DM;

/**
 * Created by Hugo on 11/06/2017.
 */
public enum PeriodicidadAccion {
    NINGUNA,
    HORA,
    DIA,
    SEMANA,
    MES;

    public int periodicidadASegundos(){



        switch (this) {
            case SEMANA:
                return 604800;
            case MES:
                return 2419200;
            case DIA:
                return 86400;
            case HORA:
                return 3600;

            default: return 0;

        }
    }

}
