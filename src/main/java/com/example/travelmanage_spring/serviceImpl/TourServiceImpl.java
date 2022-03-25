package com.example.travelmanage_spring.serviceImpl;

import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.repositorys.TourRepository;
import com.example.travelmanage_spring.services.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TourServiceImpl implements TourService {

    @Autowired
    TourRepository tourRepository;

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> findAll(Sort sort) {
        return tourRepository.findAll(sort);
    }

    @Override
    public Tour findTourById(int id) {
        return tourRepository.findTourById(id);
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }
}
