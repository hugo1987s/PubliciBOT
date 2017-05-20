package com.PubliciBot.Services;

import com.PubliciBot.DM.Tag;
import com.vaadin.ui.Tree;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by alumnos on 18/05/2017.
 */
    public class TreeService {


     /*   public Tree crearTree(){

        }*/

        public boolean exists(Tree arbol, Tag tag){
            boolean ret = false;
            for(Tag t : obtenerTodos(arbol))
            {
                if (t.getNombre().equals(tag.getNombre()))
                    ret = true;

            }

            return ret;
        }


    public ArrayList<Tag> convertiraTags(Collection<?> lista){
        ArrayList<Object> rootIds =
                new ArrayList<Object>(lista);
        ArrayList<Tag> tags =
                new ArrayList<Tag>();
        for(Object item : rootIds){
            Tag tag=(Tag) item;
            tags.add(tag);
        }
        return tags;
    }

    public ArrayList<Tag> obtenerRaices(Tree arbol){
        return convertiraTags(arbol.rootItemIds());
    }

    public ArrayList<Tag> obtenerTodos(Tree arbol){

        ArrayList<Tag> ret = convertiraTags(arbol.getItemIds());

        return ret;
        //ArrayList<Tag> raices=obtenerRaices(arbol);
      // return recorrerRecursivamente(arbol,raices);





    }
    public ArrayList<Tag> recorrerRecursivamente(Tree arbol,ArrayList<Tag> tags){
        ArrayList<Tag> ret=new ArrayList<Tag>();
        if(tags.size()==0) {
            return tags;
        }
        for(Tag tag:tags){
            ArrayList<Tag> hijosdelTag=convertiraTags(arbol.getChildren(tag));
            ret=combinarArreglos(recorrerRecursivamente(arbol,hijosdelTag),hijosdelTag);

        }
        return ret;

    }

    public  ArrayList<Tag> combinarArreglos(ArrayList<Tag> arreglo1,ArrayList<Tag> arreglo2){
        for (Tag tag:arreglo2
             ) {
            arreglo1.add(tag);
        }
        return arreglo1;
    }



        public void agregarTag(Tree arbol, Tag tag){
       if(!exists(arbol,tag)) {
           arbol.addItem(tag);
       }

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
