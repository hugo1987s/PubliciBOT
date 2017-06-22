package com.PubliciBot.Services;

import com.PubliciBot.DAO.Neodatis.CampanaDAONeodatis;
import com.PubliciBot.DM.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hugo on 22/05/2017.
 */
public class CampanaService {

    private ArrayList<Campana> campanasGuardadas;
    private CampanaDAONeodatis campanaDao;

    public CampanaService(){
        this.campanasGuardadas = new ArrayList<>();
        this.campanaDao = new CampanaDAONeodatis();
    }

    public void establecerEstado(Campana campana, EstadoCampana estadoCampana)
    {
        campana.setEstadoCampana(estadoCampana);
    }

    public void agregarTagACampana(Campana campana, Tag tag)
    {
        campana.addTags(tag);
    }

    public void agregarAccionPublicitariaACampana(Campana campana, AccionPublicitaria accion)
    {
        campana.addAccion(accion);
    }

    public void asignarDuracion(Campana campana, int dias,UnidadMedida unidad)
    {
        campana.setDuracion(dias);
        campana.setUnidadMedida(unidad);
    }



    public void recuperarCampanas(Usuario usuario){
        this.campanasGuardadas = (ArrayList<Campana>) campanaDao.recuperarCampanas(usuario);
    }


    public synchronized List<Campana> findAll(Usuario usuario)
    {
       return  campanaDao.recuperarCampanas(usuario);

    }

    public ArrayList<Campana> getCampanasGuardadas(){
        return this.campanasGuardadas;
    }

    public void guardarCampana(Campana campana){
        this.campanaDao.guardar(campana);
    }

}
