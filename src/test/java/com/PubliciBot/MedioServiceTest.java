package com.PubliciBot;

import com.PubliciBot.DM.AccionPublicitaria;
import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.Mensaje;
import com.PubliciBot.DM.TipoMedio;
import com.PubliciBot.Services.AccionPublicitariaService;

import org.junit.Test;

/**
 * Created by Hugo on 13/06/2017.
 */
public class MedioServiceTest {


    public void enviarMailTest()
    {
        AccionPublicitaria accionPublicitaria=new AccionPublicitaria();
        Medio medio = new Medio();
        //String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();


        Mensaje mensaje = new Mensaje("Mail enviado desde enviarMailTest!!",  "src/main/resources/test.jpg");


        medio.setTipoMedio(TipoMedio.EMAIL);

        AccionPublicitariaService aps=new AccionPublicitariaService();
        AccionPublicitaria AP=new AccionPublicitaria();

        AP.setDestino("agusalexander8@gmail.com");

        aps.publicar(AP, mensaje);



    }
}
