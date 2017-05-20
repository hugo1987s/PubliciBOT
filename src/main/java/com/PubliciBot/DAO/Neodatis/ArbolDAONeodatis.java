package com.PubliciBot.DAO.Neodatis;

import com.PubliciBot.DAO.Interfaces.ArbolDAO;
import com.vaadin.ui.Tree;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.List;

/**
 * Created by Hugo on 20/05/2017.
 */
public class ArbolDAONeodatis extends DAONeodatis<Tree> implements ArbolDAO {

    @Override
    public void guardarArbol(Tree arbol) {

        ODB odb = ODBFactory.open(fileNameNeodatisDB);
        odb.store(arbol);
        odb.close();


    }


    @Override
    public Tree recuperarArbol() {
        ODB odb = ODBFactory.open(fileNameNeodatisDB);

        IQuery query = new CriteriaQuery(Tree.class);

        Objects<Tree> objs = odb.getObjects(Tree.class);
        odb.close();
        if(objs != null)
            return (Tree) objs.getFirst();

        return new Tree();

    }
}
