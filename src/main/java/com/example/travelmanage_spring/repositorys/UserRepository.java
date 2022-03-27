package com.example.travelmanage_spring.repositorys;

import com.example.travelmanage_spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email,String password);

    @Query(value = "SELECT * from  user  WHERE email = ? and password = ? and role_id != 1 ",nativeQuery = true)
    User checkLoginAdmin(String email,String password);

    User findUserById(int id);

    @Query(value = "SELECT * from  user  WHERE role_id = 2 ",nativeQuery = true)
    List<User> listEmployee();

    @Query(value = "SELECT * from  user  WHERE role_id = 1 ",nativeQuery = true)
    List<User> listCustomer();

    User save(User user);

    User findUserByUserName(String userName);

}
