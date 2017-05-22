package com.PubliciBot.Services;

import com.PubliciBot.DAO.Neodatis.ArbolDAONeodatis;
import com.PubliciBot.DM.ArbolTags;
import com.PubliciBot.DM.Tag;
import com.vaadin.ui.Tree;

import java.util.ArrayList;

/**
 * Created by alumnos on 18/05/2017.
 */
public class ArbolTagsService {

    private ArbolDAONeodatis treeDAO=new ArbolDAONeodatis();
    private ArbolTags arbolTags;

    public ArbolTagsService() {
        treeDAO = new ArbolDAONeodatis();
        arbolTags = new ArbolTags();
        this.recuperarArbol();
    }

    public void agregarTag(Tree arbol, Tag tag) {
        if (!exists(tag)) {
            arbol.addItem(tag);
            arbolTags.AgregarTag(tag);
        }
    }

    public boolean exists(Tag tag) {
        for (Tag t : arbolTags.getTags()) {
            if (t.getNombre().equals(tag.getNombre()))
               return true;
        }
       return false;
    }



    public boolean setearPadre(Tree arbol, Tag tagHijo, Tag tagPadre) {
        boolean ret = false;
        try {
            ret = arbol.setParent(tagHijo, tagPadre);
            for (Tag tg:
            this.arbolTags.getTags()) {
                if(tg.equals(tagHijo)){
                    tg.setTagPadre(tagPadre);
                }

            }
        } catch (Exception e) {
            throw new IllegalArgumentException("No se encuentra el tag padre o hijo");
        }
        return ret;
    }

    private boolean quitarTagTree(Tree arbol, Tag tag) {

            boolean hasChildren = arbol.getChildren(tag) != null;
            if (hasChildren) {
                    Object[] children = arbol.getChildren(tag).toArray();

                        for (Object o: children) {
                            Tag child=(Tag) o;
                            quitarTagTree(arbol,child);
                        }


            }
            return arbol.removeItem(tag);

    }


    public void quitarTag(Tree arbol, Tag tag){
        quitarTagTree(arbol,tag);
        quitarTagArbol(this.arbolTags,tag);
    }

    private void quitarTagArbol(ArbolTags arbolTags, Tag tag){
        ArrayList<Tag> hijos = buscarTagPorPadre(arbolTags,tag);
        for(Tag tagtemp : hijos){
            quitarTagArbol(arbolTags, tagtemp);
            arbolTags.getTags().remove(tagtemp);
        }
        arbolTags.getTags().remove(tag);
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


    public void guardarArbol() {
        treeDAO.guardar(arbolTags);
    }

    public ArrayList<Tag> buscarTagPorPadre(ArbolTags arbol, Tag tagPadre) {
        ArrayList<Tag> ret = new ArrayList<Tag>();
        for (Tag tagTemp : arbol.getTags()) {
            if(tagTemp.getPadre() != null) {
                if (tagTemp.getPadre().equals(tagPadre))
                    ret.add(tagTemp);
            }
        }

        return ret;
    }


    public void recuperarArbol() {
        this.arbolTags = treeDAO.recuperar();

    }




    public Tree convertirArbolaTree (Tree treeVaadin) {
        //Limpio el arbol para no repetir los items
        treeVaadin.removeAllItems();


        for (Tag tag : arbolTags.getTags()) {
            treeVaadin.addItem(tag);
        }

        for (Tag tag : arbolTags.getTags()) {
            if (tag.getPadre() != null)
                treeVaadin.setParent(tag,tag.getPadre());
        }

        return treeVaadin;
    }


    public ArbolTags getArbolTags() {
        return arbolTags;
    }

    public void setArbolTags(ArbolTags arbolTags) {
        this.arbolTags = arbolTags;
    }
}
