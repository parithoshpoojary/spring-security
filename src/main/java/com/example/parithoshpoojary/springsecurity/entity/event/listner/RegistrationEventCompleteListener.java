package com.example.parithoshpoojary.springsecurity.entity.event.listner;

import com.example.parithoshpoojary.springsecurity.entity.User;
import com.example.parithoshpoojary.springsecurity.entity.event.RegistrationCompleteEvent;
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

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Creating the verification token for the user with the URL.
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(token, user);

        // Send mail to the user to verify the login/signup
        String url = event.getApplicationUrl() + "/verifyEmail?token=" + token;

        // Testing the verification process locally.
        TODO: // Implement this whole verification thing using a mail client.
              // https://github.com/dailycodebuffer/Spring-MVC-Tutorials/tree/master/Spring-Email-Client/src/main/java/com/dailycodebuffer/springemailclient
        log.info("Click on the link to verify your account: {}", url);
    }
}
