package com.PubliciBot;

import com.PubliciBot.DM.ArbolTags;
import com.PubliciBot.DM.Tag;
import com.PubliciBot.Services.ArbolTagsService;
import com.vaadin.ui.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by agusa on 5/22/2017.
 */
public class ArbolTagsServiceTest {

    @Test
    public void existsTest(){
        Tag a = new Tag("A");
        Tag b = new Tag("A");
        b.setTagPadre(a);

        ArbolTagsService arbol = new ArbolTagsService();
        arbol.agregarTag(a);
        Assert.assertTrue(arbol.exists(b));

        b = new Tag("B");
        Assert.assertFalse(arbol.exists(b));
    }
    @Test
    public void agregarTagsTest(){
        ArbolTagsService arbol = new ArbolTagsService();

        Tag a = new Tag("A");
        arbol.agregarTag(a);
        Assert.assertTrue(arbol.getArbolTags().getTags().contains(a));

    }
    @Test
    public void convertirArbolaTreeTest(){
        ArbolTagsService arbol = new ArbolTagsService();
        Tree treeVaadin = new Tree();

        Tag a = new Tag("A");
        arbol.agregarTag(a);
        arbol.convertirArbolaTree(treeVaadin);

        Collection<Object> tagsTree = (Collection<Object>) treeVaadin.getItemIds();
        boolean estaEnTree = false;
        for(Object o : tagsTree){
            Tag temp = (Tag) o;
            if(arbol.getArbolTags().getTags().contains(temp)){
                estaEnTree = true;
            }
        }
        Assert.assertTrue(estaEnTree);
    }


    @Test
    public void quitarTagArbolTagsTest(){
        ArbolTagsService arbol=new ArbolTagsService();

        Tag a = new Tag("A");
        arbol.agregarTag(a);
        arbol.quitarTagArbolTags(a);

        Assert.assertFalse(arbol.exists(a));
        Assert.assertFalse(arbol.getArbolTags().getTags().contains(a));

    }



    @Test
    public void setearPadreTest(){
        ArbolTagsService arbol = new ArbolTagsService();
        arbol.setArbolTags(new ArbolTags());

        Tag tagPadre = new Tag ("Padre");
        Tag tagHijo1 = new Tag ("Hijo 1");
        Tag tagHijo2 = new Tag ("Hijo 2");
        arbol.agregarTag(tagPadre);
        arbol.agregarTag(tagHijo1);
        arbol.agregarTag(tagHijo2);
        arbol.setearPadre(tagHijo1,tagPadre);
        arbol.setearPadre(tagHijo2,tagPadre);

        Assert.assertEquals(tagPadre,tagHijo1.getPadre());
        Assert.assertEquals(tagPadre,tagHijo2.getPadre());
        Assert.assertNull(tagPadre.getPadre());
    }

    @Test
    public void buscarTagPorPadreTest() {
        ArbolTagsService arbol = new ArbolTagsService();
        arbol.setArbolTags(new ArbolTags());

        Tag tagPadre = new Tag ("Padre");
        Tag tagHijo1 = new Tag ("Hijo 1");
        Tag tagHijo2 = new Tag ("Hijo 2");
        arbol.agregarTag(tagPadre);
        arbol.agregarTag(tagHijo1);
        arbol.agregarTag(tagHijo2);
        arbol.setearPadre(tagHijo1,tagPadre);
        arbol.setearPadre(tagHijo2,tagPadre);


        ArrayList<Tag> hijos = arbol.buscarTagPorPadre(tagPadre);
        Assert.assertEquals(2,hijos.size());
    }


    @Test
    public void DAOTest() {
        ArbolTagsService arbol = new ArbolTagsService();
        //PARA NO SOBREESCRIBIR LA DB
        ArbolTagsService arbolDeDB = new ArbolTagsService();
        arbolDeDB.recuperarArbol();
        arbol.setArbolTags(new ArbolTags());


        Tag tagPadre = new Tag ("Padre");
        Tag h1p1 = new Tag ("Hijo 1 P1");
        Tag h2p1 = new Tag ("Hijo 2 P1");
        Tag tagPadre2 = new Tag ("Padre 2");
        Tag h1p2 = new Tag ("Hijo 1 P2");

        arbol.agregarTag(tagPadre);
        arbol.agregarTag(h1p1);
        arbol.agregarTag(h2p1);
        arbol.agregarTag(tagPadre2);
        arbol.agregarTag(h1p2);

        arbol.setearPadre(h1p1,tagPadre);
        arbol.setearPadre(h2p1,tagPadre);
        arbol.setearPadre(h1p2,tagPadre2);

        arbol.guardarArbol();
        arbol.recuperarArbol();
        Assert.assertEquals(5,arbol.getArbolTags().getTags().size());
        arbol.setArbolTags(new ArbolTags());
        arbol.guardarArbol();

        //DEVOLVER A LA DB A SU ESTADO PREVIO AL TEST
        arbolDeDB.guardarArbol();

    }
}
