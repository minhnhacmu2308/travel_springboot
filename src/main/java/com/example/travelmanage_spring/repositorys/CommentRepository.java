package com.example.travelmanage_spring.repositorys;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Comment;
import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
    List<Comment> findCommentByPost(Post post);
    List<Comment> findAll();

}
