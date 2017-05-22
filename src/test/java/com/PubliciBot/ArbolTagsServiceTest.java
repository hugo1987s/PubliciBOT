package com.PubliciBot;

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
        Tag a=new Tag("A");
        Tag b=new Tag("A");
        b.setTagPadre(a);
        ArbolTagsService arbol=new ArbolTagsService();
        Tree tree=new Tree();
        arbol.agregarTag(tree,a);
        Assert.assertTrue(arbol.exists(b));
        b=new Tag("B");
        Assert.assertFalse(arbol.exists(b));
        arbol.quitarTag(tree,a);

    }
    @Test
    public void agregarTagsTest(){
        ArbolTagsService arbol=new ArbolTagsService();
        Tree tree=new Tree();
        Tag a=new Tag("A");
        arbol.agregarTag(tree,a);
        Assert.assertTrue(arbol.getArbolTags().getTags().contains(a));
        Collection<Object> tgs=new ArrayList<Object>(tree.getItemIds());
        boolean estaenTree=false;
        for(Object o:tgs){
            Tag temp=(Tag) o;
            if(temp.equals(a)){
                estaenTree=true;
            }
        }

        Assert.assertTrue(estaenTree);


        arbol.quitarTag(tree,a);
    }
    @Test
    public void convertirArbolaTreeTest(){
        ArbolTagsService arbol=new ArbolTagsService();
        Tree tree=new Tree();
        Tag a=new Tag("A");
        arbol.agregarTag(tree,a);
        Collection<Object> tgs=new ArrayList<Object>(arbol.convertirArbolaTree(tree).getItemIds());
        boolean estaenTree=false;

        for(Object o:tgs){
            Tag temp=(Tag) o;
            if(arbol.getArbolTags().getTags().contains(temp)){
                estaenTree=true;
            }
        }
        Assert.assertTrue(estaenTree);
        arbol.quitarTag(tree,a);

    }


    @Test
    public void quitarTagTreeTest(){

        ArbolTagsService arbol=new ArbolTagsService();
        Tree tree=new Tree();
        Tag a=new Tag("A");
        arbol.agregarTag(tree,a);
        arbol.quitarTag(tree,a);
        Collection<Object> tgs=new ArrayList<Object>(tree.getItemIds());

        boolean estaenTree=false;
        for(Object o:tgs){
            Tag temp=(Tag) o;
            if(temp.equals(a)){
                estaenTree=true;
            }
        }

        Assert.assertFalse(estaenTree);



    }
    @Test
    public void quitarTagArbolTest(){

        ArbolTagsService arbol=new ArbolTagsService();
        Tree tree=new Tree();
        Tag a=new Tag("A");
        arbol.agregarTag(tree,a);
        Collection<Object> tgs=new ArrayList<Object>(tree.getItemIds());
        arbol.quitarTag(tree,a);
        Assert.assertFalse(arbol.getArbolTags().getTags().contains(a));

    }

    @Test
    public void setearPadreTest(){
        ArbolTagsService arbol = new ArbolTagsService();
        Tree tree = new Tree();
        Tag tagPadre = new Tag ("Padre");
        Tag tagHijo1 = new Tag ("Hijo 1");
        Tag tagHijo2 = new Tag ("Hijo 2");
        arbol.agregarTag(tree,tagPadre);
        arbol.agregarTag(tree,tagHijo1);
        arbol.agregarTag(tree,tagHijo2);
        arbol.setearPadre(tree,tagHijo1,tagPadre);
        arbol.setearPadre(tree,tagHijo2,tagPadre);

        Collection<Object> tgs=new ArrayList<Object>(tree.getItemIds());
        Assert.assertEquals(3,tgs.size());

        ArrayList<Tag> hijos = arbol.buscarTagPorPadre(arbol.getArbolTags(),tagPadre);
        Assert.assertEquals(2,hijos.size());
    }

    @Test
    public void buscarTagPorPadreTest() {
        ArbolTagsService arbol = new ArbolTagsService();
        Tree tree = new Tree();
        Tag tagPadre = new Tag ("Padre");
        Tag tagHijo1 = new Tag ("Hijo 1");
        Tag tagHijo2 = new Tag ("Hijo 2");
        arbol.agregarTag(tree,tagPadre);
        arbol.agregarTag(tree,tagHijo1);
        arbol.agregarTag(tree,tagHijo2);
        arbol.setearPadre(tree,tagHijo1,tagPadre);
        arbol.setearPadre(tree,tagHijo2,tagPadre);

        Collection<Object> tgs=new ArrayList<Object>(tree.getItemIds());
        Assert.assertEquals(3,tgs.size());

        ArrayList<Tag> hijos = arbol.buscarTagPorPadre(arbol.getArbolTags(),tagPadre);
        Assert.assertEquals(2,hijos.size());
    }


    @Test
    public void DAOTest(){
        ArbolTagsService arbol = new ArbolTagsService();
        Tree tree = new Tree();
        Tag tagPadre = new Tag ("Padre");
        Tag h1p1 = new Tag ("Hijo 1 P1");
        Tag h2p1 = new Tag ("Hijo 2 P1");
        Tag tagPadre2 = new Tag ("Padre 2");
        Tag h1p2 = new Tag ("Hijo 1 P2");

        arbol.agregarTag(tree,tagPadre);
        arbol.agregarTag(tree,h1p1);
        arbol.agregarTag(tree,h2p1);
        arbol.agregarTag(tree,tagPadre2);
        arbol.agregarTag(tree,h1p2);

        arbol.setearPadre(tree,h1p1,tagPadre);
        arbol.setearPadre(tree,h2p1,tagPadre);
        arbol.setearPadre(tree,h1p2,tagPadre2);


        arbol.guardarArbol();
        arbol.recuperarArbol();
        Assert.assertEquals(5,arbol.getArbolTags().getTags().size());

    }
}
