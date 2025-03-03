package com.eauts.ems.Eauts_management.repository;

import com.eauts.ems.Eauts_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}