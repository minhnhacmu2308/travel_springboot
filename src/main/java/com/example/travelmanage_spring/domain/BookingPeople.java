package com.example.travelmanage_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "booking_people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPeople {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "booking_id",referencedColumnName = "id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "people_id",referencedColumnName = "id")
    private People people;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private int status;
}
