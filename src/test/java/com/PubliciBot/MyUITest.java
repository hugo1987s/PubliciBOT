package com.PubliciBot;

import org.junit.Assert;
import org.junit.Test;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

/**
 * Created by agusa on 5/6/2017.
 */
public class MyUITest {

    @Test
    public void cargarObjetoNeodatis(){
        ODB odb = null;
        ObjetoNeodatisEjemplo obj=new ObjetoNeodatisEjemplo("UN OBJETO");
        try
        {
            odb = ODBFactory.open("clase1db");
            odb.store(obj);
            Objects<ObjetoNeodatisEjemplo> ej = odb.getObjects(ObjetoNeodatisEjemplo.class);

            Assert.assertEquals(ej.getFirst().nombre,"UN OBJETO");

        }

        finally{
            Assert.assertNotNull(odb);
            if(odb!=null){
                odb.close();
            }

        }


    }

    }



