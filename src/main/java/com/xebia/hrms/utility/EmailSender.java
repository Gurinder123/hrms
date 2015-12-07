package com.xebia.hrms.utility;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Component
public class EmailSender {

    @Value("${spring.hrms.senderemail}")
    private String userMail;

    @Value("${spring.hrms.senderpassword}")
    private String password;

    @Value("${spring.hrms.template.location.birthday}")
    private String templateLocationBirthday;

    @Value("${spring.hrms.template.location.anniversary}")
    private String templateLocationAnniversary;

    @Value("${spring.hrms.template.location.confirmation}")
    private String templateLocationConfirmation;

    private String subject;

    private static final Logger logger = Logger.getLogger(EmailSender.class);

    public void processEmail(String name, String emailId, String occassion) {

        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;

        String templateLocation = null;

        String currentPathOfExecutingJar = null;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
//      properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.host", "outlook.office365.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userMail, password);
            }
        });

        if (occassion.equals("birthday")) {
            subject = "Happy Birthday " + name;
            templateLocation = templateLocationBirthday;
        } else if (occassion.equals("anniversary")) {
            String[] info = occassion.split(" ");
            subject = "Congratulations " + name + " for completing " + info[1] + " year with xebia";
            templateLocation = templateLocationAnniversary;
        } else if (occassion.equals("confirmation")) {
            subject = "Confirmation Mail";
            templateLocation = templateLocationConfirmation;
        }

        try {
            String sCurrentLine;

            try {
                currentPathOfExecutingJar = new File(EmailSender.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getAbsolutePath();
            } catch (URISyntaxException e) {
                logger.error(e);
            }


            bufferedReader = new BufferedReader(new FileReader(currentPathOfExecutingJar + templateLocation));
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                if (sCurrentLine.contains("Birthday")) {
                    String arr[] = sCurrentLine.split(" ");
                    for (String text : arr) {
                        if (text.equals("Birthday")) {
                            sCurrentLine = sCurrentLine + " " + name;
                            break;
                        }
                    }
                }
                stringBuffer.append(sCurrentLine);
            }
        } catch (FileNotFoundException e) {
            logger.error(e + " file not found");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e + " IO exception");
            e.printStackTrace();
        }


        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userMail));
            logger.info(emailId + " receiving mail");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
            message.setText("heloo");
            message.setSubject(subject);
            message.setContent(stringBuffer.toString(), "text/html");


            Transport.send(message);
            logger.info("Email Sent successfully....");


        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }


}
