package com.example.VBS.repository;

import com.example.VBS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmailId(String email);
    Optional<User> findByMobNo(String mobNo);
}
