package com.email;

import com.configuration.ConfigurationConstants;

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
    private Session session;

    public EmailSender() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ConfigurationConstants.MY_EMAIL_ADDRESS, ConfigurationConstants.MY_EMAIL_PASSWORD);
                    }
                });
    }

    public void sendEmail(int recordsNumber) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ConfigurationConstants.MY_EMAIL_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ConfigurationConstants.TO_RECIPIENTS_LIST));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ConfigurationConstants.CC_RECIPIENTS_LIST));

            message.setSubject("SQDF transactions report for " + getToday());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(ConfigurationConstants.getMailBody(recordsNumber));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(ConfigurationConstants.FILE_PATH + ConfigurationConstants.FILE_NAME);

            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(ConfigurationConstants.FILE_NAME);
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
