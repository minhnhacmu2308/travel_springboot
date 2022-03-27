package com.example.travelmanage_spring.services;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Comment;
import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.Tour;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> findCommentByPost(Post post);
    List<Comment> findAll();
    Comment save(Comment comment);
}
