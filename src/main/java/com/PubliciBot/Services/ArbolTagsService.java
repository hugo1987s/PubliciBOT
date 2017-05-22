package com.PubliciBot.Services;

import com.PubliciBot.DAO.Interfaces.ArbolDAO;
import com.PubliciBot.DAO.Neodatis.ArbolDAONeodatis;
import com.PubliciBot.DM.ArbolTags;
import com.PubliciBot.DM.Tag;
import com.vaadin.ui.Tree;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by alumnos on 18/05/2017.
 */
public class ArbolTagsService {

    ArbolDAO treeDAO;

    public ArbolTagsService() {
        treeDAO = new ArbolDAONeodatis();
    }

    public void agregarTag(Tree arbol, Tag tag) {
        if (!exists(arbol, tag)) {
            arbol.addItem(tag);
        }
    }

    public boolean exists(Tree arbol, Tag tag) {
        boolean ret = false;
        ArrayList<Tag> tagsArbol = obtenerTodos(arbol);
        for (Tag t : tagsArbol) {
            if (t.getNombre().equals(tag.getNombre()))
                ret = true;
        }
        return ret;
    }

    public ArrayList<Tag> obtenerTodos(Tree arbol) {
        ArrayList<Tag> ret = convertiraTags(arbol.getItemIds());
        return ret;
        //ArrayList<Tag> raices=obtenerRaices(arbol);
        // return recorrerRecursivamente(arbol,raices);
    }

    public ArrayList<Tag> convertiraTags(Collection<?> lista) {
        ArrayList<Object> rootIds =
                new ArrayList<Object>(lista);
        ArrayList<Tag> tags =
                new ArrayList<Tag>();
        for (Object item : rootIds) {
            Tag tag = (Tag) item;
            tags.add(tag);
        }
        return tags;
    }

    public ArrayList<Tag> obtenerRaices(Tree arbol) {
        return convertiraTags(arbol.rootItemIds());
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

    public boolean quitarTag(Tree arbol, Tag tag) {
        if (exists(arbol, tag)) {
            boolean hasChildren = arbol.getChildren(tag) != null;
            if (hasChildren) {
                int amountchildren = arbol.getChildren(tag).size();
                if (amountchildren > 0) {
                    Object[] children = arbol.getChildren(tag).toArray();
                    if (children.length > 0) {
                        for (int i = 0; i < children.length; i++) {
                            quitarTag( arbol, (Tag) children[i] );
                        }
                    }
                }
            }
            return arbol.removeItem(tag);
        }
        return false;
    }


    //Metodo recursivo para obtener items (ya solucionado )
    public ArrayList<Tag> recorrerRecursivamente(Tree arbol,ArrayList<Tag> tags){
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
    }
    //Fin metodo recursivo para obtener items (ya solucionado )


    public void guardarArbol(ArbolTags arbol) {
        treeDAO.guardar(arbol);
    }

    public Tag buscarTagPorPadre(ArbolTags arbol, String idTagPadre) {
        for (Tag tagTemp : arbol.getTags()) {
            if (tagTemp.getNombre().equals(idTagPadre))
                return tagTemp;
        }

        return null;
    }


    public ArbolTags recuperarArbol() {
        return treeDAO.recuperarArbol();
    }

}
