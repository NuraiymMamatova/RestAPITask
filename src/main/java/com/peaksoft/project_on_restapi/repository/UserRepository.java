package com.peaksoft.project_on_restapi.repository;

import com.peaksoft.project_on_restapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);
    @Query("select count(u) from User u")
    Long countOfUsers();
}