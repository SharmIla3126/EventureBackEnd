package com.ford.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Signup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_no")
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private UserType type; // user or event organizer
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    public enum UserType {
        user, organizer
    }
}
