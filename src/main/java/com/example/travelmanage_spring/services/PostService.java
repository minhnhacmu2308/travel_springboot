package com.example.travelmanage_spring.services;

import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findAll(Sort sort);
    Post findPostById(int id);
    Post save(Post post);
    int update(String title, String desciption , String image, int id);
    int delete(int id);
}
