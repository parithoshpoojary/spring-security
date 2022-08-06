package com.example.parithoshpoojary.springsecurity.entity.event.listner;

import com.example.parithoshpoojary.springsecurity.entity.User;
import com.example.parithoshpoojary.springsecurity.entity.event.RegistrationCompleteEvent;
import com.example.parithoshpoojary.springsecurity.service.EmailSenderService;
import com.example.parithoshpoojary.springsecurity.service.UserService;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationEventCompleteListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    private static final String subject = "Verify your email";

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Creating the verification token for the user with the URL.
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token, user);

        // url pattern
        String url = event.getApplicationUrl() + "/verifyEmail?token=" + token;

        // Testing the verification process locally (uncomment to use it).
        // log.info("Click on the link to verify your account: {}", url);

        // Sending the verification mail to the user's mail id.
        emailSenderService.sendVerificationEmail(user.getEmail(), subject, "Click on the link to verify your email address - " + url + " .");

    }
}
