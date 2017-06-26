package com.PubliciBot.DM;

/**
 * Created by Hugo on 25/05/2017.
 */
public enum DuracionCampana {
    SEMANA,
    MES,
    BIMESTRE,
    SEMESTRE;
    @Override
    public String toString(){
        switch (this){
            case MES: return "Mes";
            case SEMANA: return "Semana";
            case BIMESTRE: return "Bimestre";
            case SEMESTRE: return "Semestre";
            default: return "";


        }
    }

}
