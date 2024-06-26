package com.example.springrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String address;
}
