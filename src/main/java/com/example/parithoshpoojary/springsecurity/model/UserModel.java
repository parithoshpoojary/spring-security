package com.example.parithoshpoojary.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchpassword;

}
