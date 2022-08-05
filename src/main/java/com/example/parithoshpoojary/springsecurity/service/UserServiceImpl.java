package com.example.parithoshpoojary.springsecurity.service;

import com.example.parithoshpoojary.springsecurity.entity.User;
import com.example.parithoshpoojary.springsecurity.entity.VerificationToken;
import com.example.parithoshpoojary.springsecurity.model.UserModel;
import com.example.parithoshpoojary.springsecurity.repository.UserRepository;
import com.example.parithoshpoojary.springsecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setRole("USER");

        // Encryption the password to be saved in the database - logic in the config package.
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null){
            return "Invalid";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if(verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0){
            verificationTokenRepository.delete(verificationToken);
            return "Token is expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "Valid - Successfully Registration";
    }

}
