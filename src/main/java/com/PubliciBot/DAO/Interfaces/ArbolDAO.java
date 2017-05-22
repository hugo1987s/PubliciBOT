package com.PubliciBot.DAO.Interfaces;


import com.PubliciBot.DM.ArbolTags;
import com.vaadin.ui.Tree;

/**
 * Created by Hugo on 20/05/2017.
 */
public interface ArbolDAO extends DAO<Tree> {

    ArbolTags recuperarArbol();
    void PersistirArbol(ArbolTags arbol);
}
