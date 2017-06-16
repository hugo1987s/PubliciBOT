package com.PubliciBot.Services;

import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.Mensaje;
import com.PubliciBot.DM.TipoMedio;
import com.vaadin.server.VaadinService;
import javafx.concurrent.Task;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by Hugo on 13/06/2017.
 */
public class MedioService  {





    public void publicar(Medio medioLocal, Mensaje mensajeLocal) {
       TipoMedio tipo=medioLocal.getTipoMedio();


        switch (tipo) {
            case EMAIL: enviarMail(medioLocal,mensajeLocal) ;
                break;

            case TWITTER:
                break;


        }

    }





    private boolean enviarMail(Medio medioLocal, Mensaje mensajeLocal)
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("megafonomailer","IvoVirginia2017");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("megafonomailer@gmail.com"));
            message.setSubject("Megafono Mailer te envia una mensaje!");

            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(medioLocal.getEmailDestino()));

            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("megafonomailer@gmail.com"));

            /////////////////////////////// MAIL HTML CON IMAGEN  ///////////////////////////////

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>" + mensajeLocal.getTextoMensaje() + "<img src=\"cid:image\">";
            try {
                messageBodyPart.setContent(htmlText, "text/html");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            // add it
            multipart.addBodyPart(messageBodyPart);

            if(mensajeLocal.getImagenMensajePath() !=null && mensajeLocal.getImagenMensajePath().trim() != "")
            {
                // second part (the image)
                messageBodyPart = new MimeBodyPart();

                DataSource fds = new FileDataSource(mensajeLocal.getImagenMensajePath());

                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<image>");

                // add image to the multipart
                multipart.addBodyPart(messageBodyPart);
            }

            // put everything together
            message.setContent(multipart);

            ///////////////////////////////////////////////////////////////////////////////////////

            /////////////////////////////// MAIL COMUN - SOLO TEXTO ///////////////////////////////

            //message.setText("Girls!," +
            //		"\n\n The Megafono mailer is now running! Welcome onboard");
            ///////////////////////////////////////////////////////////////////////////////////////

            Transport.send(message);

            System.out.println("Enviado");
            return true;

        } catch (MessagingException e) {
            return false;
            //throw new RuntimeException(e);

        }
    }

    private boolean enviarTelegram(Medio medioLocal, Mensaje mensajeLocal)
    {
        return false;
    }

   private boolean enviarWhatsApp(Medio medioLocal, Mensaje mensajeLocal)
    {
        return false;
    }

    private boolean postearFacebook(Medio medioLocal, Mensaje mensajeLocal)
    {
        return false;
    }

    private boolean postearLinkedIn(Medio medioLocal, Mensaje mensajeLocal)
    {
        return false;
    }

   private boolean enviarTwitter(Medio medioLocal, Mensaje mensajeLocal)
    {
        return false;
    }


}
