package com.example.travelmanage_spring.services;

import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TourService {
    List<Tour> findAll();
    List<Tour> findAll(Sort sort);
    Tour findTourById(int id);
    Tour save(Tour tour);
    int update(String name, String desciption , int cost, String image, String due_time, String start_date, String address, int id);
    int delete(int id);
    List<Tour> findTourByTitleOrPrice(String keyWord);
    List<Tour> searchDate(String keyWord);
    Page<Tour> findAll(Pageable page);
}
