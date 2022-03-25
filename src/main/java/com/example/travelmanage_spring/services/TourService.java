package com.example.travelmanage_spring.services;

import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TourService {
    List<Tour> findAll();
    List<Tour> findAll(Sort sort);
    Tour findTourById(int id);
    Tour save(Tour tour);
}
