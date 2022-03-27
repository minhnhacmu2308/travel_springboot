package com.example.travelmanage_spring.repositorys;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {
    List<Booking> findBookingByTour(Tour tour);

    List<Booking> findAll();

}
