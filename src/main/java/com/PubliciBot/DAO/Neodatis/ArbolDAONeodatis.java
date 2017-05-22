package com.PubliciBot.DAO.Neodatis;

import com.PubliciBot.DAO.Interfaces.ArbolDAO;
import com.PubliciBot.DM.ArbolTags;
import com.vaadin.ui.Tree;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.List;

/**
 * Created by Hugo on 20/05/2017.
 */
public class ArbolDAONeodatis extends DAONeodatis<Tree> implements ArbolDAO {



    @Override
    public void PersistirArbol(ArbolTags arbol) {
        ODB odb = ODBFactory.open(fileNameNeodatisDB);
        odb.store(arbol);
        odb.close();
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
