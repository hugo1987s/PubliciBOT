package com.PubliciBot.DAO.Neodatis;

import com.PubliciBot.DAO.Interfaces.ArbolDAO;
import com.PubliciBot.DM.ArbolTags;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.ArrayList;

/**
 * Created by Hugo on 20/05/2017.
 */
public class ArbolDAONeodatis extends DAONeodatis<ArbolTags> implements ArbolDAO {




    public void guardar(ArbolTags arbol) {
       try {
           ODB odb = ODBFactory.open(fileNameNeodatisDB);
           ArrayList<Object> objs =
                   new ArrayList<Object>(odb.getObjects(ArbolTags.class));
           for (Object o : objs) {
               odb.delete(o);

           }
            odb.store(arbol);
            odb.close();
       }
       catch (Exception e){
           e.printStackTrace();
       }

    }


    @Override
    public ArbolTags recuperarArbol() {
        ODB odb = ODBFactory.open(fileNameNeodatisDB);

        IQuery query = new CriteriaQuery(ArbolTags.class);

        Objects<ArbolTags> objs = odb.getObjects(ArbolTags.class);
        odb.close();

        if (objs != null && objs.size() != 0)
            return (ArbolTags) objs.getFirst();

        return new ArbolTags();
    }

}
