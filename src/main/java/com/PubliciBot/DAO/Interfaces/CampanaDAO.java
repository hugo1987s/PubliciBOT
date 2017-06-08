package com.PubliciBot.DAO.Interfaces;

import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Usuario;

import java.util.List;

/**
 * Created by Max on 6/4/2017.
 */
public interface CampanaDAO extends DAO<Campana> {

    List<Campana> recuperarCampanas(Usuario usuario);
}
