package com.example.labbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "classroom_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true, name="room_number")
    private String roomNum;

    @Column(name="building_name")
    private String buildingName;

    @Column(name="total_capacity")
    private int totalCapacity;

    private String description;

    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate = new Date();



}
