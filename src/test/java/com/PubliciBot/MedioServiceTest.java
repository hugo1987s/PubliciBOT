package com.PubliciBot;

import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.Mensaje;
import com.PubliciBot.DM.TipoPost;
import com.PubliciBot.Services.MedioService;
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
        Mensaje mensaje = new Mensaje("Mail enviado desde enviarMailTest!!", "C:/Users/Hugo/IdeaProjects/PubliciBot/src/main/webapp/VAADIN/images/postman.jpg");

        medio.setEmailDestino("maxilen334@gmail.com");
        medio.setTipoPost(TipoPost.EMAIL);

        medioService.enviarMail(medio, mensaje);



    }
}
