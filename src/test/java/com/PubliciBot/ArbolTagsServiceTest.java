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
    public void exists(){
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
    public void agregarTags(){
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
    public void convertirArbolaTree(){
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
    public void quitarTagTree(){

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
    public void quitarTagArbol(){

        ArbolTagsService arbol=new ArbolTagsService();
        Tree tree=new Tree();
        Tag a=new Tag("A");
        arbol.agregarTag(tree,a);
        Collection<Object> tgs=new ArrayList<Object>(tree.getItemIds());
        arbol.quitarTag(tree,a);
        Assert.assertFalse(arbol.getArbolTags().getTags().contains(a));



    }




}
