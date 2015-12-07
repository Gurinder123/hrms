package com.xebia.hrms.service.impl;

import com.xebia.hrms.model.Employee;
import com.xebia.hrms.service.EmailService;
import com.xebia.hrms.utility.EmailSender;
import com.xebia.hrms.utility.ExcelParser;
import org.apache.commons.collections4.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by gurinder on 3/12/15.
 */

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    ExcelParser excelParser;

    @Autowired
    EmailSender emailSender;

    private static final Logger logger = Logger.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail() {
        final Map<Employee, List> listMap = excelParser.read();

        if (MapUtils.isNotEmpty(listMap)) {
            for (Map.Entry<Employee, List> entry : listMap.entrySet()) {
                sendEmailToOutLook(((Employee) entry.getKey()).getName(), ((Employee) entry.getKey()).getEmailId(), entry.getValue());
            }
        }
    }

    private void sendEmailToOutLook(String name, String emailId, List<String> value) {
        for (String val : value) {
            emailSender.processEmail(name, emailId, val);
            logger.info(name + " " + emailId + " " + value);
        }
    }


}