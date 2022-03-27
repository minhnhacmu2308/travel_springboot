package com.example.travelmanage_spring.serviceImpl;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.repositorys.BookingRepository;
import com.example.travelmanage_spring.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public List<Booking> findBookingByTour(Tour tour) {
        return bookingRepository.findBookingByTour(tour);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

}
