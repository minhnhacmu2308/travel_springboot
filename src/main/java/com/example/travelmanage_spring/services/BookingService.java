package com.example.travelmanage_spring.services;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Tour;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  BookingService {
    Booking save(Booking booking);
    List<Booking> findBookingByTour(Tour tour);
    List<Booking> findAll();
    List<Booking> findBookingByUserId(int id);
}
