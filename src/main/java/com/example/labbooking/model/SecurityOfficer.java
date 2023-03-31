package com.example.labbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "officer_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityOfficer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String email;

    @Column(name="phone_number")
    private String phoneNum;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate = new Date();


}
