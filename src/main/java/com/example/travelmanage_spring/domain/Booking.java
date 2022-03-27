package com.example.travelmanage_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user_b;

    @ManyToOne
    @JoinColumn(name = "tour_id",referencedColumnName = "id")
    private Tour tour;

    @Column(name = "quantity_adults")
    private int quantityAdults;

    @Column(name = "quantity_chirld")
    private int quantityChirld;

    @Column(name = "createdAt")
    private String createdAt;

    @Column(name = "status")
    private int status;
}
