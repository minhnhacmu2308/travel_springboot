package com.example.travelmanage_spring.serviceImpl;

import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.repositorys.PostRepository;
import com.example.travelmanage_spring.repositorys.TourRepository;
import com.example.travelmanage_spring.services.PostService;
import com.example.travelmanage_spring.services.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAll(Sort sort) {
        return postRepository.findAll(sort);
    }

    @Override
    public Post findPostById(int id) {
        return postRepository.findPostById(id);
    }

    @Override
    public Post save(Post Post) {
        return postRepository.save(Post);
    }

    @Override
    public int update(String title, String desciption , String image, int id) {
        return postRepository.update(title,desciption,image,id);
    }

    @Override
    public int delete(int id) {
        return postRepository.delete(id);
    }
}
