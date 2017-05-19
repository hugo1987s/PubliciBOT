package com.PubliciBot.Services;

import com.PubliciBot.DM.Tag;
import com.vaadin.data.Item;
import com.vaadin.ui.Tree;

/**
 * Created by alumnos on 18/05/2017.
 */
public class TreeService {

    public void agregarTag(Tree arbol, Tag tag){
        arbol.addItem(arbol);
    }
    public void agregarTag(Tree arbol, Tag tagPadre,Tag tagHijo){
        arbol.setParent(tagHijo,tagPadre);
    }
    public boolean quitarTag(Tree arbol, Tag tag){
       return  arbol.removeItem(tag);
    }


}
