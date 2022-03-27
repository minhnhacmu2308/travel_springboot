package com.example.travelmanage_spring.repositorys;

import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int id);
    List<Post> findAll();

    Post save(Post post);

    @Modifying
    @Transactional
    @Query(value = "Update post SET title = ?, description = ? ,image = ? WHERE id = ?",nativeQuery = true)
    int update(String title, String desciption , String image, int id);

    @Modifying
    @Transactional
    @Query(value = "Delete From post WHERE id = ?",nativeQuery = true)
    int delete( int id);
}
