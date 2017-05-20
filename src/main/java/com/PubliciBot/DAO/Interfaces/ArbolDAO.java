package com.PubliciBot.DAO.Interfaces;


import com.vaadin.ui.Tree;

/**
 * Created by Hugo on 20/05/2017.
 */
public interface ArbolDAO extends DAO<Tree> {

    void guardarArbol(Tree arbol);
    Tree recuperarArbol ();
}
