package com.PubliciBot.DAO.Neodatis;

import com.PubliciBot.DAO.Interfaces.CampanaDAO;
import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Usuario;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

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
            IQuery usuarioDeCampana = new CriteriaQuery(Campana.class, Where.equal("usuario", usuario));
            //TODO revisar porque no funciona la query
            Objects<Object> campanasDeUsuario = odb.getObjects(usuarioDeCampana);
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            odb.close();
        }
        return new ArrayList<>();
    }
}
