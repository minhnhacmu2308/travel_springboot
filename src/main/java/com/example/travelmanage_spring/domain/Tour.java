package com.example.travelmanage_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tour")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "due_time")
    private String dueTime;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private int price;

    @Column(name = "view")
    private int view;

    @OneToMany(mappedBy = "tour")
    private List<Booking> bookings;

    @Column(name = "status")
    private int status;
}
