package com.PubliciBot;

import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.Mensaje;
import com.PubliciBot.DM.TipoMedio;
import com.PubliciBot.Services.MedioService;
import org.junit.Test;

/**
 * Created by Hugo on 13/06/2017.
 */
public class MedioServiceTest {


    public void enviarMailTest()
    {

        Medio medio = new Medio();
        //String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();


        Mensaje mensaje = new Mensaje("Mail enviado desde enviarMailTest!!",  "src/main/resources/test.jpg");

        medio.setEmailDestino("agusalexander8@gmail.com");
        medio.setTipoMedio(TipoMedio.EMAIL);

        MedioService medioService = new MedioService();
        medioService.publicar(medio, mensaje);



    }
}
