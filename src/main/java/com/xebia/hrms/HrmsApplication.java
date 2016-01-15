package com.xebia.hrms;

import com.xebia.hrms.model.Employee;
import com.xebia.hrms.service.EmailService;
import com.xebia.hrms.utility.EmailSender;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HrmsApplication implements CommandLineRunner {

    @Autowired
    EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        emailService.sendEmail();

    }

}
