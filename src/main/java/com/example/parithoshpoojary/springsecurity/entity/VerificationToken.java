package com.example.parithoshpoojary.springsecurity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {

    //Defining the expiration time for the token to be verified.
    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, length = 11)
     private UUID id;
     private String token;
     private Date expirationTime;
     @OneToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
     private User user;

     // Constructor's
     public VerificationToken(User user, String token){
         super();
         this.user = user;
         this.token = token;
         this.expirationTime = calculateExpiryTime(EXPIRATION_TIME);
     }
     public VerificationToken(String token){
         super();
         this.token =token;
         this.expirationTime = calculateExpiryTime(EXPIRATION_TIME);
     }

     // Validation Methods
    private Date calculateExpiryTime(int expirationTime) {
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(new Date().getTime());
        calender.add(Calendar.MINUTE, expirationTime);
        return new Date((calender.getTime().getTime()));
    }
}
