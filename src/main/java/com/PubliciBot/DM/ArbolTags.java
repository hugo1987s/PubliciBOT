package com.PubliciBot.DM;

import com.vaadin.ui.Tree;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.ArrayList;

/**
 * Created by Hugo on 21/05/2017.
 */
public class ArbolTags {
    private ArrayList<Tag> tags;

    public ArbolTags() {
        this.tags = new ArrayList<>();
    }

    public ArbolTags(ArrayList<Tag> nodos) {
        this.tags = nodos;
    }

    public void AgregarTag(Tag tag)
    {
        this.tags.add(tag);
    }

    public void AgregarTag(String nombre, String tagPadre)
    {
        this.tags.add(new Tag(nombre, tagPadre));
    }


    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }


}
