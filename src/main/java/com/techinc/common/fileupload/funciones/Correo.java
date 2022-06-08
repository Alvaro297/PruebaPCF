package com.techinc.common.fileupload.funciones;

import com.techinc.common.fileupload.storage.StorageProperties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Correo {

    private final Path rootLocation;

    public Correo(StorageProperties rootLocation) {
        this.rootLocation = Paths.get(rootLocation.getLocation());;
    }

    public void correoJava() throws MessagingException {
        Path destinationFile = this.rootLocation.toAbsolutePath();
        String correo="alvarofalagan29@gmail.com";
        String contra="Alvaro2908";
        String correoDestino="alvaromartinfalagan29@gmail.com";

        Properties p =new Properties();
        p.put("mail.smtp.host","smtp.office365.com");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.trust", "smtp.office365.com");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.user",correo);
        p.setProperty("mail.smtp.auth", "true");
        Session s = Session.getDefaultInstance(p);
        BodyPart text =new MimeBodyPart();
        text.setText("Ejemplo");
        BodyPart adjunto =new MimeBodyPart();
        adjunto.setDataHandler(new DataHandler(new FileDataSource(destinationFile+"/Java.zip")));
        adjunto.setFileName("Buenodia");
        MimeMultipart m=new MimeMultipart();
        m.addBodyPart(text);
        m.addBodyPart(adjunto);

        MimeMessage mensaje = new MimeMessage(s);
        mensaje.setFrom(new InternetAddress(correo));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
        mensaje.setSubject("Envio de archivos adjuntos");
        mensaje.setContent(m);

        Transport t = s.getTransport("smtp");
        t.connect(correo,contra);
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }

    public void correoKotlin() throws MessagingException {
        Path destinationFile = this.rootLocation.toAbsolutePath();

        String correo="alvarofalagan29@gmail.com";
        String contra="Alvaro2908";
        String correoDestino="alvaromartinfalagan29@gmail.com";

        Properties p =new Properties();
        p.put("mail.smtp.host","smtp.office365.com");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.trust", "smtp.office365.com");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.user",correo);
        p.setProperty("mail.smtp.auth", "true");
        Session s = Session.getDefaultInstance(p);
        BodyPart text =new MimeBodyPart();
        text.setText("Ejemplo");
        BodyPart adjunto =new MimeBodyPart();
        adjunto.setDataHandler(new DataHandler(new FileDataSource(destinationFile+"/Animal.zip")));
        adjunto.setFileName("Buenodia");
        MimeMultipart m=new MimeMultipart();
        m.addBodyPart(text);
        m.addBodyPart(adjunto);

        MimeMessage mensaje = new MimeMessage(s);
        mensaje.setFrom(new InternetAddress(correo));
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
        mensaje.setSubject("Envio de archivos adjuntos");
        mensaje.setContent(m);

        Transport t = s.getTransport("smtp");
        t.connect(correo,contra);
        t.sendMessage(mensaje, mensaje.getAllRecipients());
        t.close();
    }
}
