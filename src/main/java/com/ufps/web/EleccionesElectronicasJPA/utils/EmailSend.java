package com.ufps.web.EleccionesElectronicasJPA.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSend {
  private final String from = "kevinandrestg7@gmail.com";
  Session session;
  Properties properties = System.getProperties();
  
  public EmailSend() {
    String host = "smtp.gmail.com";
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");
  }
  
  public void sendMessage(String subject, String messageToSend, String to) {
    session = Session.getInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, "ujvzwdfucwcvdijs");
      }
    });
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(subject);
      message.setContent("<a href=\"" + messageToSend + "\" target=\"_blank\">Por favor click aqui para hacer la votacion</a>", "text/html");
      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }
}
