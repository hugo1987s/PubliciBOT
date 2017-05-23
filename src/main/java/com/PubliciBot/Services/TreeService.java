package com.PubliciBot.Services;

import com.PubliciBot.DM.Tag;
import com.vaadin.ui.Tree;

import java.util.Collection;

/**
 * Created by Max on 5/23/2017.
 */
public class TreeService {


    public TreeService() {

    }

    public void agregarTag(Tree arbol, Tag tag) {
        if (!exists(arbol,tag)) {
            arbol.addItem(tag);
        }
    }

    public boolean exists(Tree arbol, Tag tag) {
        Collection<Object> treeTags = (Collection<Object>) arbol.getItemIds();
        for (Object t :treeTags) {
            Tag auxTag = (Tag) t;
            if (auxTag.equals(tag))
                return true;
        }
        return false;
    }

    public boolean setearPadre(Tree arbol, Tag tagHijo, Tag tagPadre) {
        boolean ret = false;
        try {
            ret = arbol.setParent(tagHijo, tagPadre);
        } catch (Exception e) {
            throw new IllegalArgumentException("No se encuentra el tag padre o hijo");
        }
        return ret;
    }

    public boolean quitarTagTree(Tree arbol, Tag tag) {
        boolean hasChildren = arbol.getChildren(tag) != null;
        if (hasChildren) {
            Object[] children = arbol.getChildren(tag).toArray();
            for (Object o: children) {
                Tag child =(Tag) o;
                quitarTagTree(arbol,child);
            }
        }
        return arbol.removeItem(tag);
    }


    //Metodo recursivo para obtener items (ya solucionado )
  /*  public ArrayList<Tag> recorrerRecursivamente(Tree arbol,ArrayList<Tag> tags){
        ArrayList<Tag> ret = new ArrayList<Tag>();

        for(Tag tag : tags){
            ArrayList<Tag> hijosdelTag = convertiraTags(arbol.getChildren(tag));
            if(hijosdelTag.size() == 0)
                ret=new ArrayList<Tag>();
            else {
                hijosdelTag.addAll(recorrerRecursivamente(arbol, hijosdelTag));
                ret = hijosdelTag;
            }
        }
        return ret;
    }*/
    //Fin metodo recursivo para obtener items (ya solucionado )
}
