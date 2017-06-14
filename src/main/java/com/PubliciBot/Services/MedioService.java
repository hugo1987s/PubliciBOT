package com.PubliciBot.Services;

import com.PubliciBot.DM.Medio;
import com.PubliciBot.DM.Mensaje;
import com.vaadin.server.VaadinService;

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
public class MedioService {

    public void enviarMail(Medio medio, Mensaje mensaje)
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
                    InternetAddress.parse(medio.getEmailDestino()));

            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("megafonomailer@gmail.com"));

            /////////////////////////////// MAIL HTML CON IMAGEN  ///////////////////////////////

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>" + mensaje.getTextoMensaje() + "<img src=\"cid:image\">";
            try {
                messageBodyPart.setContent(htmlText, "text/html");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();

            //String basedir = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            //String basedir = "";
            /*
            if (VaadinService.getCurrent() != null) {
                basedir = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            } else {
                basedir = "WebContent";
            }
*/
            //basedir = "C:/Users/Hugo/IdeaProjects/PubliciBot/src/main/webapp";

            //C://Users//Hugo//IdeaProjects//PubliciBot//src//main//webapp//VAADIN\images

            DataSource fds = new FileDataSource(mensaje.getImagenMensajePath());

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            // put everything together
            message.setContent(multipart);

            ///////////////////////////////////////////////////////////////////////////////////////

            /////////////////////////////// MAIL COMUN - SOLO TEXTO ///////////////////////////////

            //message.setText("Girls!," +
            //		"\n\n The Megafono mailer is now running! Welcome onboard");
            ///////////////////////////////////////////////////////////////////////////////////////

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
