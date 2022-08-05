package com.example.parithoshpoojary.springsecurity.service;

import com.example.parithoshpoojary.springsecurity.entity.User;
import com.example.parithoshpoojary.springsecurity.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
    void saveVerificationToken(String token, User user);
    String validateVerificationToken(String token);
}
