package com.ford.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String name;
    private String email;
    private String phoneNo;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;




}
