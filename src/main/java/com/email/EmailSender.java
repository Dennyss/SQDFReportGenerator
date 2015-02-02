package com.email;

import com.configuration.Configuration;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Denys Kovalenko on 12/30/2014.
 */
public class EmailSender {

    public void sendEmail(int recordsNumber, String fileName) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Configuration.MY_EMAIL_ADDRESS, Configuration.MY_EMAIL_PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Configuration.MY_EMAIL_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Configuration.TO_RECIPIENTS_LIST));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(Configuration.CC_RECIPIENTS_LIST));

            message.setSubject("SQDF transactions report for " + getToday());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(Configuration.getMailBody(recordsNumber));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(Configuration.FILE_PATH + fileName);

            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getToday() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormatter.format(new Date(System.currentTimeMillis()));
    }
}
