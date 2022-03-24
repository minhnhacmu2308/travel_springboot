package com.example.travelmanage_spring.repositorys;


import com.example.travelmanage_spring.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleById(int id);
}
