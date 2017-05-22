package com.PubliciBot;

import com.PubliciBot.DAO.Neodatis.ArbolDAONeodatis;
import com.PubliciBot.DM.ArbolTags;
import org.junit.Assert;
import org.junit.Test;
import org.neodatis.odb.Objects;

/**
 * Created by agusa on 5/6/2017.
 */
public class ArbolDaoNeodatisTest {

    @Test
    public void guardar(){
        ArbolTags obj=new ArbolTags();
        ArbolDAONeodatis dao=new ArbolDAONeodatis();
        try
        {
        dao.guardar(obj); //Duplicado para probar que se guarda una vez
        dao.guardar(obj);
        Objects<ArbolTags> ej = dao.obtenerTodos(ArbolTags.class);

        Assert.assertEquals(1,ej.size());}
        catch (Exception e){
            e.printStackTrace();
        }


    }




    }





