package com.example.travelmanage_spring.serviceImpl;

import com.example.travelmanage_spring.domain.Role;
import com.example.travelmanage_spring.domain.User;
import com.example.travelmanage_spring.repositorys.RoleRepository;
import com.example.travelmanage_spring.repositorys.UserRepository;
import com.example.travelmanage_spring.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User checkEmailExist(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User checkLogin(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User checkLoginAdmin(String email, String password) {
        return userRepository.checkLoginAdmin(email,password);
    }

    @Override
    public Role findRoleById(int id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
