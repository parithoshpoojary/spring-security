package com.example.parithoshpoojary.springsecurity.controller;

import com.example.parithoshpoojary.springsecurity.entity.User;
import com.example.parithoshpoojary.springsecurity.entity.event.RegistrationCompleteEvent;
import com.example.parithoshpoojary.springsecurity.model.UserModel;
import com.example.parithoshpoojary.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class RegistrationController {

    Logger loggerFactory = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        log.debug("Trying to register the user by verifying the email");
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Verification URL sent the registered mail.";
    }

    @GetMapping("/verifyEmail")
    public String verifyRegistrationStatus(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("Valid - Successfully Registration")) {
            return "User Successfully Registered with the application.";
        }
        return "User was not registered with the application";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
