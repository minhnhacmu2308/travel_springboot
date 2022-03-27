package com.example.travelmanage_spring.repositorys;

import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    Tour findTourById(int id);
    List<Tour> findAll();

    Tour save(Tour tour);

    @Modifying
    @Transactional
    @Query(value = "Update tour SET name = ?, description = ? , price = ?,image = ?, due_time = ?, start_date = ?, address = ? WHERE id = ?",nativeQuery = true)
    int update(String name, String desciption , int cost, String image, String due_time, String start_date, String address, int id);

    @Modifying
    @Transactional
    @Query(value = "Delete From tour WHERE id = ?",nativeQuery = true)
    int delete( int id);
}
