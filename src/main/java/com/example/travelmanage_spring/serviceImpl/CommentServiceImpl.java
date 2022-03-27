package com.example.travelmanage_spring.serviceImpl;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Comment;
import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.repositorys.BookingRepository;
import com.example.travelmanage_spring.repositorys.CommentRepository;
import com.example.travelmanage_spring.services.BookingService;
import com.example.travelmanage_spring.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentByPost(Post post) {
        return commentRepository.findCommentByPost(post);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }


}
