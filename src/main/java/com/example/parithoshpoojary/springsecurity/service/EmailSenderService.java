package com.example.parithoshpoojary.springsecurity.service;

public interface EmailSenderService {

    public void sendVerificationEmail(String toEmail, String subject, String body);
}
