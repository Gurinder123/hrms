package com.xebia.hrms.utility;

import com.xebia.hrms.model.Property;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by jaspreet on 10/12/15.
 */
public class PropertyLoader {

    Properties mainProperties;
    Property property;
    FileInputStream file;
    //String path = "/home/xeadmin/application.properties";
    String path = "/home/jaspreet/skypedownloads/application.properties";
    //String path = "C:/hrmsAutomation/application.properties";

    public Property loadProperties() {

        mainProperties = new Properties();
        try {
            file = new FileInputStream(path);
            mainProperties.load(file);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setProperties();
        return property;
    }

    private void setProperties() {
        property = new Property();

        property.setUserMail(mainProperties.getProperty("spring.hrms.senderemail"));
        property.setPassword(mainProperties.getProperty("spring.hrms.senderpassword"));
        property.setTemplateLocationBirthday(mainProperties.getProperty("spring.hrms.template.location.birthday"));
        property.setTemplateLocationAnniversary(mainProperties.getProperty("spring.hrms.template.location.anniversary"));
        property.setTemplateLocationConfirmation(mainProperties.getProperty("spring.hrms.template.location.confirmation"));
        property.setStuff(mainProperties.getProperty("spring.hrms.stuff"));
        property.setProbationReviewForm(mainProperties.getProperty("spring.hrms.template.location.probationReviewForm"));
        property.setProbationConfirmationGuide(mainProperties.getProperty("spring.hrms.template.location.probationConfirmationGuide"));
        property.setAllIndiaEmailId(mainProperties.getProperty("spring.hrms.allIndiaEmailId"));
        property.setXlsxfileLocation(mainProperties.getProperty("spring.hrms.xlsxfile"));
        property.setHrindiaEmailId(mainProperties.getProperty("spring.hrms.hrIndiaEmailId"));
        property.setConfirmationSchedulerDate(Integer.parseInt(mainProperties.getProperty("spring.hrms.confirmatiom.schedule.date")));
        property.setDiviyaEmailId(mainProperties.getProperty("spring.hrms.confirmation.DiviyaEmailId"));
        property.setMahuaEmailId(mainProperties.getProperty("spring.hrms.confirmation.MahuaEmailId"));
    }
}


