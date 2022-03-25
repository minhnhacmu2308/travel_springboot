package com.example.travelmanage_spring.repositorys;

import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    Tour findTourById(int id);
}
