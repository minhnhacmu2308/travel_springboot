package com.example.travelmanage_spring.services;




import com.example.travelmanage_spring.domain.Role;
import com.example.travelmanage_spring.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User save(User user);

    User checkEmailExist(String email);

    User checkLogin(String email,String password);

    User getUserById(int id);

    User checkLoginAdmin(String email,String password);

    Role findRoleById(int id);

    User findUserByUserName(String userName);

    void delete(int id);
}
