package com.PubliciBot.DAO.Neodatis;

import com.PubliciBot.DAO.Interfaces.CampanaDAO;
import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Usuario;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 6/4/2017.
 */
public class CampanaDAONeodatis extends DAONeodatis<Campana> implements CampanaDAO {

    @Override
    public List<Campana> recuperarCampanas(Usuario usuario) {
        ODB odb = null;
        try {
            odb = ODBFactory.open(fileNameNeodatisDB);
            IQuery usuarioDeCampana = new SimpleNativeQuery(){
                public boolean match(Campana campana) {
                    return
                            campana.getUsuario().equals(usuario);
                }
            };
            //TODO revisar porque no funciona la query
            Objects<Campana> campanasDeUsuario = odb.getObjects(usuarioDeCampana);
            ArrayList<Campana> ret = new ArrayList<>();
            ret.addAll(campanasDeUsuario);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            odb.close();
        }
        return new ArrayList<>();
    }
}
