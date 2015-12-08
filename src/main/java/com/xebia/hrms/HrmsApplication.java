package com.xebia.hrms;

import com.xebia.hrms.service.EmailService;
import com.xebia.hrms.utility.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrmsApplication implements CommandLineRunner {

    @Autowired
    EmailService emailService;

    @Autowired
    EmailSender emailSender;

    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        emailService.sendEmail();
        emailSender.processEmail("Gurinder singh", "gurindersingh@xebia.com", "anniversary 1");
    }

}
