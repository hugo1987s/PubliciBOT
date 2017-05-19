package com.PubliciBot.Services;

import com.PubliciBot.DM.Tag;
import com.vaadin.ui.Tree;

/**
 * Created by alumnos on 18/05/2017.
 */
    public class TreeService {


     /*   public Tree crearTree(){

        }*/

        public void agregarTag(Tree arbol, Tag tag){
            if(arbol.getItem(tag)!=null){
            //TODO
            }
            arbol.addItem(arbol);
        }
    public boolean setearPadre(Tree arbol, Tag tagHijo,Tag tagPadre){
        boolean ret=false ;
            try {
            ret = arbol.setParent(tagHijo, tagPadre);

    }
       catch (Exception e){
           throw new IllegalArgumentException("No se encuentra el tag padre o hijo");
       }
            return ret;
}

    public boolean quitarTag(Tree arbol, Tag tag){
        return  arbol.removeItem(tag);
        }


    }
