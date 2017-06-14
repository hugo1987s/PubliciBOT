package com.PubliciBot;

import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.Mensaje;
import com.PubliciBot.DM.TipoPost;
import com.PubliciBot.Services.MedioService;
import com.vaadin.server.VaadinService;
import org.junit.Test;

/**
 * Created by Hugo on 13/06/2017.
 */
public class MedioServiceTest {

    @Test
    public void enviarMailTest()
    {
        MedioService medioService = new MedioService();
        Medio medio = new Medio();
        //String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();


        Mensaje mensaje = new Mensaje("Mail enviado desde enviarMailTest!!",  "src/main/resources/test.jpg");

        medio.setEmailDestino("agusalexander8@gmail.com");
        medio.setTipoPost(TipoPost.EMAIL);

        medioService.enviarMail(medio, mensaje);



    }
}
