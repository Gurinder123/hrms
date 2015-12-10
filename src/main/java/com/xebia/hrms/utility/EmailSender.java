package com.xebia.hrms.utility;

import com.xebia.hrms.model.Employee;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Date;
import java.util.Properties;


@Component
public class EmailSender {

    private String userMail;

    private String password;

    private String templateLocationBirthday;

    private String templateLocationAnniversary;

    private String templateLocationConfirmation;

    private String probationConfirmationGuide;

    private String probationReviewForm;

    private String subject;

    private String stuff;

    public EmailSender() {
        Properties properties = loadProperties();
        userMail = properties.getProperty("spring.hrms.senderemail");
        password = properties.getProperty("spring.hrms.senderpassword");
        templateLocationBirthday = properties.getProperty("spring.hrms.template.location.birthday");
        templateLocationAnniversary = properties.getProperty("spring.hrms.template.location.anniversary");
        templateLocationConfirmation = properties.getProperty("spring.hrms.template.location.confirmation");
        stuff = properties.getProperty("spring.hrms.stuff");
        probationReviewForm = properties.getProperty("spring.hrms.template.location.probationReviewForm");
        probationConfirmationGuide = properties.getProperty("spring.hrms.template.location.probationConfirmationGuide");
    }

    private static final Logger logger = Logger.getLogger(EmailSender.class);

    public void processEmail(Employee employee, String occassion) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;

        String templateLocation = null;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "outlook.office365.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userMail, password);
            }
        });

        if (occassion.equals("birthday")) {
            subject = "Happy Birthday " + employee.getName();
            templateLocation = templateLocationBirthday;
        } else if (occassion.contains("anniversary")) {
            String[] info = occassion.split(" ");
            if (Integer.parseInt(info[1]) > 1) {
                subject = "Congratulations " + employee.getName() + " for completing " + info[1] + " years with xebia";
            } else {
                subject = "Congratulations " + employee.getName() + " for completing " + info[1] + " year with xebia";
            }
            templateLocation = templateLocationAnniversary;
        } else if (occassion.equals("confirmation")) {
            subject = "Confirmation Mail";
            templateLocation = templateLocationConfirmation;
        }

        try {
            String sCurrentLine;


            bufferedReader = new BufferedReader(new FileReader(stuff + templateLocation));
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                if (sCurrentLine.contains("Birthday")) {
                    String arr[] = sCurrentLine.split(" ");
                    for (String text : arr) {
                        if (text.equals("Birthday")) {
                            sCurrentLine = sCurrentLine + " " + employee.getName();
                            break;
                        }
                    }
                }
                if (sCurrentLine.contains("5-years") && occassion.contains("anniversary")) {
                    String info[] = occassion.split(" ");
                    sCurrentLine = sCurrentLine.replace("5-years", info[1]);
                } else if (sCurrentLine.contains("completion")) {
                    String info[] = occassion.split(" ");
                    if (Integer.parseInt(info[1]) > 1) {
                        sCurrentLine = sCurrentLine.replace("Name", employee.getName());
                        sCurrentLine = sCurrentLine.replace("years", info[1] + " years");
                    } else {
                        sCurrentLine = sCurrentLine.replace("Name", employee.getName());
                        sCurrentLine = sCurrentLine.replace("years", info[1] + " year");
                    }
                }
                if (sCurrentLine.contains("Reporting_manager") && occassion.equals("confirmation")) {
                    sCurrentLine = sCurrentLine.replace("Reporting_manager", employee.getReportingManager());
                }
                if (sCurrentLine.contains("Employee_name") && occassion.equals("confirmation")) {
                    sCurrentLine = sCurrentLine.replace("Employee_name", employee.getName());
                    sCurrentLine = sCurrentLine.replace("Date_of_probation", employee.getProbationEndDate().toString());
                }
                if (sCurrentLine.contains("15_days") && occassion.equals("confirmation")) {
                    DateTime dateTime = new DateTime(new Date());
                    dateTime = dateTime.plusDays(15);
                    sCurrentLine = sCurrentLine.replace("15_days", dateTime.getDayOfMonth() + "/" + dateTime.getMonthOfYear() + "/" + dateTime.getYear());
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
            logger.info(employee.getEmailId() + " receiving mail");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getEmailId()));
            message.setSubject(subject);
            if (occassion.equals("confirmation")) {
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(stringBuffer.toString(), "text/html");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                attachFiles(multipart);
                message.setContent(multipart);
            } else {
                message.setContent(stringBuffer.toString(), "text/html");
            }

            Transport.send(message);
            logger.info("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


    private void attachFiles(Multipart multipart) throws MessagingException {
        MimeBodyPart attachReviewForm = new MimeBodyPart();
        MimeBodyPart attachConfirmationGuide = new MimeBodyPart();

        try {
            attachReviewForm.attachFile(stuff + probationReviewForm);
            attachConfirmationGuide.attachFile(stuff + probationConfirmationGuide);
        } catch (IOException ex) {
            logger.error(ex);
        }
        multipart.addBodyPart(attachReviewForm);
        multipart.addBodyPart(attachConfirmationGuide);
    }

    private Properties loadProperties() {

        Properties mainProperties = new Properties();

        FileInputStream file = null;

        String path = "/home/gurinder/Downloads/application.properties";

        try {
            file = new FileInputStream(path);
            mainProperties.load(file);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mainProperties;
    }
}



